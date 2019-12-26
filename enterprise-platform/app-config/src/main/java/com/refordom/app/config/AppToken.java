package com.refordom.app.config;

import com.refordom.app.model.AppDetails;
import com.refordom.app.model.enums.AppEnum;

/**
 * @author pricess.wang
 * @date 2019/12/26 18:01
 */
public interface AppToken {

    AppDetails getAppDetails();

    String getAccessToken();

    AppEnum getAppType();
}
