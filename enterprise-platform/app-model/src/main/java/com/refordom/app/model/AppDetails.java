package com.refordom.app.model;

import com.refordom.app.model.enums.AppEnum;

import java.io.Serializable;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:39
 */
public interface AppDetails extends Serializable {

    String getAppId();

    String getAppSecret();

    String getLogo();

    String getAppName();

    AppEnum getAppType();

    String getAppExplain();

    String getOwner();
}
