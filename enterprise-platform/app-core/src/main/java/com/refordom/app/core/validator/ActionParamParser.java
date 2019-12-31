package com.refordom.app.core.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/31 19:39
 */
public interface ActionParamParser {

    ParamBean build(HttpServletRequest request);
}
