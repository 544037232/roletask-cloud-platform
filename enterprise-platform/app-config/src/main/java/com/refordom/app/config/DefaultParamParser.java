package com.refordom.app.config;

import com.pricess.omc.validator.ParamAdapter;
import com.pricess.omc.validator.ParamParser;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的参数解析器
 *
 * @author pricess.wang
 * @date 2019/12/31 19:40
 */
public class DefaultParamParser implements ParamParser {

    @Override
    public ParamAdapter build(HttpServletRequest request) {
        return new DefaultParamAdapter(request);
    }
}
