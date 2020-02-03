package com.refordom.app.service.shelves.upper;

import com.refordom.common.action.builder.validator.ActionParamParser;
import com.refordom.common.action.builder.validator.ParamAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * 上架的参数解析器
 *
 * @author pricess.wang
 * @date 2019/12/31 19:44
 */
public class UpperShelfParamParser implements ActionParamParser {

    @Override
    public ParamAdapter build(HttpServletRequest request) {
        return new UpperShelfParamAdapter(request);
    }
}
