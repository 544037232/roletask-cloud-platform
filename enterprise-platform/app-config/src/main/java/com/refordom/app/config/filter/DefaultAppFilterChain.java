package com.refordom.app.config.filter;

import com.refordom.app.config.AppFilterChain;
import com.refordom.app.config.AppRequestMatcher;
import com.refordom.app.config.AppStoreProvider;
import org.springframework.transaction.PlatformTransactionManager;

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

    private final AppRequestMatcher requestMatcher;

    private final List<Filter> filters;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter;

    private final List<AppStoreProvider> storeProviders;

    private final String actionName;

    private final PlatformTransactionManager transactionManager;

    public DefaultAppFilterChain(String actionName,
                                 boolean continueChainBeforeSuccessfulFilter,
                                 PlatformTransactionManager transactionManager,
                                 List<AppStoreProvider> storeProviders,
                                 AppRequestMatcher requestMatcher, Filter... filters) {
        this(actionName, continueChainBeforeSuccessfulFilter, transactionManager, storeProviders, requestMatcher, Arrays.asList(filters));
    }

    public DefaultAppFilterChain(String actionName,
                                 boolean continueChainBeforeSuccessfulFilter,
                                 PlatformTransactionManager transactionManager,
                                 List<AppStoreProvider> storeProviders,
                                 AppRequestMatcher requestMatcher,
                                 List<Filter> filters) {
        this.actionName = actionName;
        this.transactionManager = transactionManager;
        this.storeProviders = storeProviders;
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

    public String getActionName() {
        return actionName;
    }

    @Override
    public boolean isContinueChainBeforeSuccessfulFilter() {
        return continueChainBeforeSuccessfulFilter;
    }

    @Override
    public List<AppStoreProvider> getStoreProviders() {
        return storeProviders;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public String toString() {
        return "[ " + requestMatcher + ", " + filters + "]";
    }
}
