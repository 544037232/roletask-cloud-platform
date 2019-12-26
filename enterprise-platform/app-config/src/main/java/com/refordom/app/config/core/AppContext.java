package com.refordom.app.config.core;

import com.refordom.app.model.AppDetails;

import java.io.Serializable;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:24
 */
public interface AppContext extends Serializable {

    AppDetails getAppDetails();

    void setAppDetails(AppDetails appDetails);
}
