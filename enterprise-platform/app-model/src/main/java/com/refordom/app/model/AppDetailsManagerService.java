package com.refordom.app.model;

import com.refordom.app.core.AppDetails;

import java.io.Serializable;

/**
 * 应用模型管理器
 *
 * @author pricess.wang
 * @date 2019/12/19 19:06
 */
public interface AppDetailsManagerService extends Serializable {

    /**
     * 根据应用ID和应用类型获取应用模型
     *
     * @param appId   应用ID
     * @return appDetails
     */
    AppDetails getApp(String appId);

    /**
     * 创建应用模型
     *
     * @param appDetails 应用模型
     */
    void createApp(AppDetails appDetails);
}
