package com.refordom.app.config;

import com.refordom.app.config.util.RequestUtils;
import com.refordom.app.core.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

    //设置事务的传播机制
    private DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);

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

        FilterChainDelegator filterChainDelegator = getFilters(request);

        if (filterChainDelegator == null) {
            if (log.isDebugEnabled()) {
                log.debug(RequestUtils.buildRequestUrl(request) + ("has no matching filters"));
            }

            chain.doFilter(request, response);

            return;
        }

        VirtualFilterChain vfc = new VirtualFilterChain(chain, filterChainDelegator, transDefinition);
        vfc.doFilter(request, response);
    }

    private FilterChainDelegator getFilters(HttpServletRequest request) {
        for (AppFilterChain chain : filterChains) {
            if (chain.matches(request)) {
                return new FilterChainDelegator(chain);
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

    private static class FilterChainDelegator {

        private final List<Filter> filters;

        private final boolean continueChainBeforeSuccessfulFilter;

        private final List<AppStoreProvider> storeProviders;

        private final PlatformTransactionManager transactionManager;

        private FilterChainDelegator(AppFilterChain appFilterChain) {
            this.filters = appFilterChain.getFilters();
            this.storeProviders = appFilterChain.getStoreProviders();
            this.continueChainBeforeSuccessfulFilter = appFilterChain.isContinueChainBeforeSuccessfulFilter();
            this.transactionManager = appFilterChain.getTransactionManager();
        }

        public PlatformTransactionManager getTransactionManager() {
            return transactionManager;
        }

        public List<Filter> getFilters() {
            return filters;
        }

        public List<AppStoreProvider> getStoreProviders() {
            return storeProviders;
        }

        public boolean isContinueChainBeforeSuccessfulFilter() {
            return continueChainBeforeSuccessfulFilter;
        }
    }

    private static class VirtualFilterChain implements FilterChain {
        private final FilterChain originalChain;
        private final int size;
        private final FilterChainDelegator filterChainDelegator;
        private int currentPosition = 0;

        private DefaultTransactionDefinition transDefinition;

        private VirtualFilterChain(FilterChain chain, FilterChainDelegator filterChainDelegator, DefaultTransactionDefinition transDefinition) {
            this.originalChain = chain;
            this.filterChainDelegator = filterChainDelegator;
            this.size = filterChainDelegator.getFilters().size();
            this.transDefinition = transDefinition;
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse response)
                throws IOException, ServletException {

            if (currentPosition == size) {

                TransactionStatus transStatus = filterChainDelegator.getTransactionManager().getTransaction(transDefinition);

                try {
                    for (AppStoreProvider appStoreProvider : filterChainDelegator.getStoreProviders()) {

                        AppContextHolder.getContext().getResult().stream()
                                .filter(t -> appStoreProvider.supports(t.getClass()))
                                .forEach(appStoreProvider::provider);
                    }

                    filterChainDelegator.getTransactionManager().commit(transStatus);

                } catch (Exception e) {

                    filterChainDelegator.getTransactionManager().rollback(transStatus);
                    throw e;
                }

                if (!filterChainDelegator.isContinueChainBeforeSuccessfulFilter()) {
                    return;
                }
                originalChain.doFilter(req, response);

            } else {
                currentPosition++;

                Filter nextFilter = filterChainDelegator.getFilters().get(currentPosition - 1);

                if (log.isDebugEnabled()) {
                    HttpServletRequest request = (HttpServletRequest) req;
                    log.debug(RequestUtils.buildRequestUrl(request)
                            + " at position " + currentPosition + " of " + size
                            + " in additional filter chain; firing Filter: '"
                            + nextFilter.getClass().getSimpleName() + "'");
                }

                nextFilter.doFilter(req, response, this);
            }
        }
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
