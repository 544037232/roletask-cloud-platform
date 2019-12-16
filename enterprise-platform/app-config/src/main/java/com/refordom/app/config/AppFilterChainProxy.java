package com.refordom.app.config;

import com.refordom.app.config.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/12 17:02
 */
@Slf4j
public class AppFilterChainProxy implements Filter, InitializingBean {
    // ~ Static fields/initializers
    // =====================================================================================

    // ~ Instance fields
    // ================================================================================================

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
                request.removeAttribute(FILTER_APPLIED);
            }
        } else {
            doFilterInternal(request, response, chain);
        }
    }

    private void doFilterInternal(ServletRequest req, ServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        List<Filter> filters = getFilters(request);

        if (filters == null || filters.size() == 0) {
            if (log.isDebugEnabled()) {
                log.debug(RequestUtils.buildRequestUrl(request)
                        + (filters == null ? " has no matching filters"
                        : " has an empty filter list"));
            }

            chain.doFilter(request, response);

            return;
        }

        VirtualFilterChain vfc = new VirtualFilterChain(chain, filters);
        vfc.doFilter(request, response);
    }

    /**
     * Returns the first filter chain matching the supplied URL.
     *
     * @param request the request to match
     * @return an ordered array of Filters defining the filter chain
     */
    private List<Filter> getFilters(HttpServletRequest request) {
        for (AppFilterChain chain : filterChains) {
            if (chain.matches(request)) {
                return chain.getFilters();
            }
        }

        return null;
    }

    /**
     * @return the list of {@code SecurityFilterChain}s which will be matched against and
     * applied to incoming requests.
     */
    public List<AppFilterChain> getFilterChains() {
        return filterChains;
    }

    /**
     * Used (internally) to specify a validation strategy for the filters in each
     * configured chain.
     *
     * @param filterChainValidator the validator instance which will be invoked on during
     *                             initialization to check the {@code FilterChainProxy} instance.
     */
    public void setFilterChainValidator(AppFilterChainProxy.FilterChainValidator filterChainValidator) {
        this.filterChainValidator = filterChainValidator;
    }

    @Override
    public String toString() {
        return "AppFilterChainProxy[" +
                "Filter Chains: " +
                filterChains +
                "]";
    }

    // ~ Inner Classes
    // ==================================================================================================

    /**
     * Internal {@code FilterChain} implementation that is used to pass a request through
     * the additional internal list of filters which match the request.
     */
    private static class VirtualFilterChain implements FilterChain {
        private final FilterChain originalChain;
        private final List<Filter> additionalFilters;
        private final int size;
        private int currentPosition = 0;

        private VirtualFilterChain(FilterChain chain, List<Filter> additionalFilters) {
            this.originalChain = chain;
            this.additionalFilters = additionalFilters;
            this.size = additionalFilters.size();
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse response)
                throws IOException, ServletException {

            if (currentPosition == size) {

                originalChain.doFilter(req, response);
            } else {
                currentPosition++;

                Filter nextFilter = additionalFilters.get(currentPosition - 1);

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
