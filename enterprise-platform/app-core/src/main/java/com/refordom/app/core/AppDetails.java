package com.refordom.app.core;

import java.io.Serializable;

/**
 * 应用详情，只包含应用的定义属性，不包含业务属性
 *
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
