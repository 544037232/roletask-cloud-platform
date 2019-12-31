package com.refordom.app.model;

import com.refordom.app.model.entity.AppHistory;

/**
 * 应用历史管理器
 *
 * @author pricess.wang
 * @date 2019/12/19 19:10
 */
public interface AppHistoryManagerService {

    /**
     * 获取应用商店版本历史
     *
     * @param appId 应用ID
     * @return AppHistory
     */
    AppHistory getHistoryApp(String appId);

    /**
     * 创建应用商店的历史版本
     *
     * @param appHistory AppHistory
     */
    void createHistoryApp(AppHistory appHistory);
}
