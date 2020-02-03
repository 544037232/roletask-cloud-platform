package com.refordom.app.config;

import com.refordom.common.action.builder.validator.ActionParamParser;
import com.refordom.common.action.builder.validator.ParamAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的参数解析器
 *
 * @author pricess.wang
 * @date 2019/12/31 19:40
 */
public class DefaultParamParser implements ActionParamParser {

    @Override
    public ParamAdapter build(HttpServletRequest request) {
        return new DefaultParamAdapter(request);
    }
}
