package com.refordom.app.config;

import com.refordom.app.config.util.RequestUtils;
import com.refordom.app.core.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 应用执行代理
 *
 * @author pricess.wang
 * @date 2019/12/12 17:02
 */
@Slf4j
public class AppFilterChainProxy implements Filter, InitializingBean {

    private final static String FILTER_APPLIED = AppFilterChainProxy.class.getName().concat(
            ".APPLIED");

    private final List<AppFilterChain> filterChains;

    private FilterChainValidator filterChainValidator = new NullFilterChainValidator();

    // ~ Methods
    // ========================================================================================================

    public AppFilterChainProxy() {
        this(Collections.emptyList());
    }

    public AppFilterChainProxy(AppFilterChain chain) {
        this(Collections.singletonList(chain));
    }

    public AppFilterChainProxy(List<AppFilterChain> filterChains) {
        this.filterChains = Collections.unmodifiableList(filterChains);
    }

    @Override
    public void afterPropertiesSet() {
        filterChainValidator.validate(this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        boolean clearContext = request.getAttribute(FILTER_APPLIED) == null;
        if (clearContext) {
            try {
                request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
                doFilterInternal(request, response, chain);
            } finally {
                AppContextHolder.clearContext();
                request.removeAttribute(FILTER_APPLIED);
            }
        } else {
            doFilterInternal(request, response, chain);
        }
    }

    private void doFilterInternal(ServletRequest req, ServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        AppFilterChain filterChain = getFilters(request);

        if (filterChain == null) {
            if (log.isDebugEnabled()) {
                log.debug(RequestUtils.buildRequestUrl(request) + ("has no matching filters"));
            }

            chain.doFilter(request, response);

            return;
        }

        filterChain.doFilter(request, response, chain);
    }

    private AppFilterChain getFilters(HttpServletRequest request) {
        for (AppFilterChain chain : filterChains) {
            if (chain.matches(request)) {
                return chain;
            }
        }
        return null;
    }

    public List<AppFilterChain> getFilterChains() {
        return filterChains;
    }

    public void setFilterChainValidator(FilterChainValidator filterChainValidator) {
        this.filterChainValidator = filterChainValidator;
    }

    @Override
    public String toString() {
        return "AppFilterChainProxy[" +
                "Filter Chains: " +
                filterChains +
                "]";
    }

    public interface FilterChainValidator {
        void validate(AppFilterChainProxy filterChainProxy);
    }

    private static class NullFilterChainValidator implements FilterChainValidator {
        @Override
        public void validate(AppFilterChainProxy filterChainProxy) {
        }
    }
}
