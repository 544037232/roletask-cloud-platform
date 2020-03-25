package com.refordom.app.service.shelves.upper;

import com.omc.builder.validator.ParamAdapter;
import com.omc.builder.validator.ParamParser;

import javax.servlet.http.HttpServletRequest;

/**
 * 上架的参数解析器
 *
 * @author pricess.wang
 * @date 2019/12/31 19:44
 */
public class UpperShelfParamParser implements ParamParser {

    @Override
    public ParamAdapter build(HttpServletRequest request) {
        return new UpperShelfParamAdapter(request);
    }
}
