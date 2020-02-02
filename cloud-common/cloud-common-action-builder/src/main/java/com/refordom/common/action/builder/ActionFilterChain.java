package com.refordom.common.action.builder;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用过滤器链
 *
 * @author pricess.wang
 * @date 2019/12/12 16:58
 */
public interface ActionFilterChain extends Filter {

    boolean matches(HttpServletRequest request);

    List<Filter> getFilters();

    List<ActionServiceProvider> getServiceProviders();

    List<ActionStoreProvider> getStoreProviders();
}
