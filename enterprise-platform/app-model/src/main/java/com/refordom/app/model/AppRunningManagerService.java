package com.refordom.app.model;

import com.refordom.app.model.entity.AppRunning;

/**
 * 应用运行管理器
 *
 * @author pricess.wang
 * @date 2019/12/19 19:09
 */
public interface AppRunningManagerService {

    /**
     * 根据应用ID与客户端ID获取正在此客户环境运行的版本
     *
     * @param appId    应用ID
     * @param clientId 客户端ID
     * @return AppRunning
     */
    AppRunning getRunningApp(String appId, String clientId);

    /**
     * 根据应用ID与客户端ID删除运行中的版本，要注意保存历史数据
     *
     * @param appId    应用ID
     * @param clientId 客户端ID
     */
    void deleteRunningApp(String appId, String clientId);

    /**
     * 创建运行中的版本
     *
     * @param appRunning runningApp
     */
    void createRunningApp(AppRunning appRunning);
}
