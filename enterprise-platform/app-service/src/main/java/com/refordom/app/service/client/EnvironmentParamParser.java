package com.refordom.app.service.client;

import com.refordom.common.action.builder.validator.ActionParamParser;
import com.refordom.common.action.builder.validator.ParamBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:47
 */
public class EnvironmentParamParser implements ActionParamParser {

    @Override
    public ParamBean build(HttpServletRequest request) {
        return new EnvironmentParam(request);
    }
}
