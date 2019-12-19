package com.refordom.app.config.core;

import com.refordom.app.config.AppDetails;
import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.model.AppModel;
import com.refordom.app.model.enums.AppEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:52
 */
public abstract class AbstractAppDetails implements AppDetails {

    private String appId;

    private String action;

    private String accessToken;

    private AppEnum appType;

    private AppModel appModel;

    public AbstractAppDetails(HttpServletRequest request) {
        this.appId = request.getParameter(AppConstant.PARAM_APP_ID);
        this.accessToken = cutToToken(request);
        this.action = cutToAction(request);
        this.appType = AppEnum.valueOf(cutToAppType(request));
        this.appModel = AppContextHolder.getContext().getAppModel();
    }

    private String cutToAppType(HttpServletRequest request) {
        String appType = request.getParameter(AppConstant.PARAM_APP_TYPE);

        return appType.toUpperCase();
    }

    private String cutToAction(HttpServletRequest request) {
        String path = request.getServletPath();

        String[] cut = path.split(AppConstant.APP_URL_PATTERNS);

        if (cut.length != 2) {
            throw new AppContextException("");
        }
        return cut[1];
    }

    private String cutToToken(HttpServletRequest request) {
        String token = request.getHeader(AppConstant.PARAM_ACCESS_TOKEN);

        if (!token.startsWith("bearer ")) {
            throw new AppContextException("");
        }
        return token.substring(7);
    }

    @Override
    public AppModel getAppModel() {
        return appModel;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getAction() {
        return action;
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
