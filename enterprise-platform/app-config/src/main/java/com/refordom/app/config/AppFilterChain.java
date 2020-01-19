package com.refordom.app.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用过滤器链
 *
 * @author pricess.wang
 * @date 2019/12/12 16:58
 */
public interface AppFilterChain extends Filter {

    boolean matches(HttpServletRequest request);

    List<Filter> getFilters();

    List<AppServiceProvider> getServiceProviders();

    List<AppStoreProvider> getStoreProviders();
}
