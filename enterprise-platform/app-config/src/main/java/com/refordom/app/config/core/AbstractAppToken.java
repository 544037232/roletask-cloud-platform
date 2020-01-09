package com.refordom.app.config.core;

import com.refordom.app.config.AppToken;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.AppDetails;
import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.core.validator.ParamBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:52
 */
public abstract class AbstractAppToken implements AppToken {

    private AppDetails appDetails;

    private String accessToken;

    private ParamBean paramBean;

    public AbstractAppToken(HttpServletRequest request) {
        this.accessToken = cutToToken(request);
        this.appDetails = AppContextHolder.getContext().getAppDetails();
        this.paramBean = AppContextHolder.getContext().getParamBean();
    }

    private String cutToToken(HttpServletRequest request) {
        String token = request.getHeader(ParamConstant.PARAM_ACCESS_TOKEN);

        if (!token.startsWith("bearer ")) {
            throw new AppContextException("");
        }
        return token.substring(7);
    }

    @Override
    public ParamBean getParamBean() {
        return paramBean;
    }

    @Override
    public AppDetails getAppDetails() {
        return appDetails;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

}
