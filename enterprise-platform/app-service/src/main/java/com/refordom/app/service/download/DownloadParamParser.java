package com.refordom.app.service.download;

import com.refordom.app.core.validator.ActionParamParser;
import com.refordom.app.core.validator.ParamBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2020/1/16 17:59
 */
public class DownloadParamParser implements ActionParamParser {

    @Override
    public ParamBean build(HttpServletRequest request) {
        return new DownloadParam(request);
    }
}
