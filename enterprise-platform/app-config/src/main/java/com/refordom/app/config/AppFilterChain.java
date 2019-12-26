package com.refordom.app.config;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/12 16:58
 */
public interface AppFilterChain {

    boolean matches(HttpServletRequest request);

    List<Filter> getFilters();

    boolean isContinueChainBeforeSuccessfulFilter();
}
