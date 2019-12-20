package com.refordom.app.config.provisioning;

import com.refordom.app.model.AppModel;
import com.refordom.app.model.enums.AppEnum;

/**
 * 应用模型管理器
 *
 * @author pricess.wang
 * @date 2019/12/19 19:06
 */
public interface AppModelManagerService {

    /**
     * 根据应用ID和应用类型获取应用模型
     *
     * @param appId   应用ID
     * @param appType 应用类型，不同的类型获取不同的模型
     * @return appModel
     */
    AppModel getModel(String appId, AppEnum appType);

    /**
     * 创建应用模型
     *
     * @param appModel 应用模型
     * @param appType 应用类型，不同的类型获取不同的模型
     */
    void createModel(AppModel appModel, AppEnum appType);
}
