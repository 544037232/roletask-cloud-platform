package com.refordom.app.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/12 17:02
 */
public class AppFilterChainProxy extends GenericFilterBean {
    // ~ Static fields/initializers
    // =====================================================================================

    private static final Log logger = LogFactory.getLog(FilterChainProxy.class);

    // ~ Instance fields
    // ================================================================================================

    private final static String FILTER_APPLIED = FilterChainProxy.class.getName().concat(
            ".APPLIED");

    private List<AppFilterChain> filterChains;

    private AppFilterChainProxy.FilterChainValidator filterChainValidator = new AppFilterChainProxy.NullFilterChainValidator();

    private HttpFirewall firewall = new StrictHttpFirewall();

    // ~ Methods
    // ========================================================================================================

    public AppFilterChainProxy() {
    }

    public AppFilterChainProxy(AppFilterChain chain) {
        this(Arrays.asList(chain));
    }

    public AppFilterChainProxy(List<AppFilterChain> filterChains) {
        this.filterChains = filterChains;
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
            }
            finally {
                SecurityContextHolder.clearContext();
                request.removeAttribute(FILTER_APPLIED);
            }
        }
        else {
            doFilterInternal(request, response, chain);
        }
    }

    private void doFilterInternal(ServletRequest request, ServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {

        FirewalledRequest fwRequest = firewall
                .getFirewalledRequest((HttpServletRequest) request);
        HttpServletResponse fwResponse = firewall
                .getFirewalledResponse((HttpServletResponse) response);

        List<Filter> filters = getFilters(fwRequest);

        if (filters == null || filters.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug(UrlUtils.buildRequestUrl(fwRequest)
                        + (filters == null ? " has no matching filters"
                        : " has an empty filter list"));
            }

            fwRequest.reset();

            chain.doFilter(fwRequest, fwResponse);

            return;
        }

        AppFilterChainProxy.VirtualFilterChain vfc = new AppFilterChainProxy.VirtualFilterChain(fwRequest, chain, filters);
        vfc.doFilter(fwRequest, fwResponse);
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
     * Convenience method, mainly for testing.
     *
     * @param url the URL
     * @return matching filter list
     */
    public List<Filter> getFilters(String url) {
        return getFilters(firewall.getFirewalledRequest((new FilterInvocation(url, "GET")
                .getRequest())));
    }

    /**
     * @return the list of {@code SecurityFilterChain}s which will be matched against and
     * applied to incoming requests.
     */
    public List<AppFilterChain> getFilterChains() {
        return Collections.unmodifiableList(filterChains);
    }

    /**
     * Used (internally) to specify a validation strategy for the filters in each
     * configured chain.
     *
     * @param filterChainValidator the validator instance which will be invoked on during
     * initialization to check the {@code FilterChainProxy} instance.
     */
    public void setFilterChainValidator(AppFilterChainProxy.FilterChainValidator filterChainValidator) {
        this.filterChainValidator = filterChainValidator;
    }

    /**
     * Sets the "firewall" implementation which will be used to validate and wrap (or
     * potentially reject) the incoming requests. The default implementation should be
     * satisfactory for most requirements.
     *
     * @param firewall
     */
    public void setFirewall(HttpFirewall firewall) {
        this.firewall = firewall;
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
        private final FirewalledRequest firewalledRequest;
        private final int size;
        private int currentPosition = 0;

        private VirtualFilterChain(FirewalledRequest firewalledRequest,
                                   FilterChain chain, List<Filter> additionalFilters) {
            this.originalChain = chain;
            this.additionalFilters = additionalFilters;
            this.size = additionalFilters.size();
            this.firewalledRequest = firewalledRequest;
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response)
                throws IOException, ServletException {
            if (currentPosition == size) {
                if (logger.isDebugEnabled()) {
                    logger.debug(UrlUtils.buildRequestUrl(firewalledRequest)
                            + " reached end of additional filter chain; proceeding with original chain");
                }

                // Deactivate path stripping as we exit the security filter chain
                this.firewalledRequest.reset();

                originalChain.doFilter(request, response);
            }
            else {
                currentPosition++;

                Filter nextFilter = additionalFilters.get(currentPosition - 1);

                if (logger.isDebugEnabled()) {
                    logger.debug(UrlUtils.buildRequestUrl(firewalledRequest)
                            + " at position " + currentPosition + " of " + size
                            + " in additional filter chain; firing Filter: '"
                            + nextFilter.getClass().getSimpleName() + "'");
                }

                nextFilter.doFilter(request, response, this);
            }
        }
    }

    public interface FilterChainValidator {
        void validate(AppFilterChainProxy filterChainProxy);
    }

    private static class NullFilterChainValidator implements AppFilterChainProxy.FilterChainValidator {
        @Override
        public void validate(AppFilterChainProxy filterChainProxy) {
        }
    }
}
