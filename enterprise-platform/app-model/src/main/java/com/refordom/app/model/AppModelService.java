package com.refordom.app.model;

/**
 * @author pricess.wang
 * @date 2019/12/20 18:59
 */
public interface AppModelService {

    AppModel getModel(String appId);

    void createModel(AppModel appModel);
}
