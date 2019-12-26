package com.refordom.app.config.filter;

import com.refordom.app.config.AppFilterChain;
import com.refordom.app.config.AppRequestMatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/12 17:10
 */
public class DefaultAppFilterChain implements AppFilterChain {

    private static final Log logger = LogFactory.getLog(DefaultAppFilterChain.class);

    private final AppRequestMatcher requestMatcher;

    private final List<Filter> filters;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter;

    public DefaultAppFilterChain(boolean continueChainBeforeSuccessfulFilter, AppRequestMatcher requestMatcher, Filter... filters) {
        this(continueChainBeforeSuccessfulFilter, requestMatcher, Arrays.asList(filters));
    }

    public DefaultAppFilterChain(boolean continueChainBeforeSuccessfulFilter, AppRequestMatcher requestMatcher, List<Filter> filters) {
        logger.info("Creating filter chain: " + requestMatcher + ", " + filters);
        this.requestMatcher = requestMatcher;
        this.filters = new ArrayList<>(filters);
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    public AppRequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public boolean matches(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }

    @Override
    public boolean isContinueChainBeforeSuccessfulFilter() {
        return continueChainBeforeSuccessfulFilter;
    }

    @Override
    public String toString() {
        return "[ " + requestMatcher + ", " + filters + "]";
    }
}
