package com.refordom.app.config;

import com.refordom.app.model.AppModel;
import com.refordom.app.model.enums.AppEnum;

import java.io.Serializable;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:39
 */
public interface AppDetails extends Serializable {

    /**
     * 获取应用Id
     * @return appId
     */
    String getAppId();

    /**
     * 获取请求接口名
     * @return action
     */
    String getAction();

    /**
     * 获取请求token
     * @return accessToken
     */
    String getAccessToken();

    /**
     * 获取应用类型
     * @return appType
     */
    AppEnum getAppType();

    /**
     * 获取应用模型
     * @return appModel
     */
    AppModel getAppModel();
}
