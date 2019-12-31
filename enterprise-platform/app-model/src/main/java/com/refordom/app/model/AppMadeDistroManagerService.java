package com.refordom.app.model;

import com.refordom.app.model.entity.AppMadeDistro;

/**
 * 客户端二次开发应用发布管理器
 *
 * @author pricess.wang
 * @date 2019/12/19 19:09
 */
public interface AppMadeDistroManagerService {

    /**
     * 根据应用ID获取客户定制的app版本
     *
     * @param appId 应用ID
     * @param clientId 客户端ID
     * @return AppDistro
     */
    AppMadeDistro getMadeDistroApp(String appId, String clientId);

    /**
     * 根据应用ID更新客户定制app的状态或其他属性
     *
     * @param appMadeDistro 发现版本
     */
    void updateMadeDistroAppByAppId(AppMadeDistro appMadeDistro);

    /**
     * 创建客户定制的发行版本
     *
     * @param appMadeDistro 发行版本
     */
    void createMadeDistroApp(AppMadeDistro appMadeDistro);

}
