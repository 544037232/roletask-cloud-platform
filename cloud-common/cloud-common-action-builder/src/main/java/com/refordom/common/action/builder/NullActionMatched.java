package com.refordom.common.action.builder;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求校验器的默认实现，默认是不处理，继承{@link ActionRequestConfigurerAdapter #config}重新config时必须配置请求路由通配符
 *
 * @author pricess.wang
 * @date 2019/12/31 17:09
 */
public class NullActionMatched implements ActionRequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        return false;
    }
}
