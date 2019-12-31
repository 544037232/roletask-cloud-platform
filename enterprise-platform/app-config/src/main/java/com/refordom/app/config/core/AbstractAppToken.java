package com.refordom.app.config.core;

import com.refordom.app.config.AppToken;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.core.AppDetails;
import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.core.AppEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:52
 */
public abstract class AbstractAppToken implements AppToken {

    private AppDetails appDetails;

    private String accessToken;

    private AppEnum appType;

    public AbstractAppToken(HttpServletRequest request) {
        this.accessToken = cutToToken(request);
        this.appType = AppEnum.valueOf(cutToAppType(request));
        this.appDetails = AppContextHolder.getContext().getAppDetails();
    }

    private String cutToAppType(HttpServletRequest request) {
        String appType = request.getParameter(AppConstant.PARAM_APP_TYPE);

        return appType.toUpperCase();
    }

    private String cutToToken(HttpServletRequest request) {
        String token = request.getHeader(AppConstant.PARAM_ACCESS_TOKEN);

        if (!token.startsWith("bearer ")) {
            throw new AppContextException("");
        }
        return token.substring(7);
    }

    @Override
    public AppDetails getAppDetails() {
        return appDetails;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public AppEnum getAppType() {
        return appType;
    }
}
