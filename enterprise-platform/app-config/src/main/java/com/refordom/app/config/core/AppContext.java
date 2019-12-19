package com.refordom.app.config.core;

import com.refordom.app.config.AppDetails;
import com.refordom.app.model.AppModel;

import java.io.Serializable;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:24
 */
public interface AppContext extends Serializable {

    AppModel getAppModel();

    void setAppModel(AppModel appModel);

    AppDetails getAppDetails();

    void setAppDetails(AppDetails appDetails);
}
