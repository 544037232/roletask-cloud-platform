package com.refordom.app.config;

import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用过滤器链
 *
 * @author pricess.wang
 * @date 2019/12/12 16:58
 */
public interface AppFilterChain {

    boolean matches(HttpServletRequest request);

    List<Filter> getFilters();

    boolean isContinueChainBeforeSuccessfulFilter();

    List<AppStoreProvider> getStoreProviders();

    PlatformTransactionManager getTransactionManager();
}
