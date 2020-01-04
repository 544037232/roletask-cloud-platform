package com.refordom.app.service.shelves.upper;

import com.refordom.app.core.validator.ActionParamParser;
import com.refordom.app.core.validator.ParamBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 上架的参数解析器
 *
 * @author pricess.wang
 * @date 2019/12/31 19:44
 */
public class UpperShelfActionParamParser implements ActionParamParser {

    @Override
    public ParamBean build(HttpServletRequest request) {
        return new UpperShelfParam(request);
    }
}
