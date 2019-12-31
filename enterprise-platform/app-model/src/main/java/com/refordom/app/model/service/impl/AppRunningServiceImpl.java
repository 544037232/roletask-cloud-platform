package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppRunningDao;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.app.model.service.AppRunningService;
import com.refordom.app.model.entity.AppRunning;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppRunningServiceImpl extends ServiceImpl<AppRunningDao, AppRunning> implements AppRunningService {

    @Override
    public AppRunning getRunningApp(String appId, String clientId) {
        return this.getOne(Wrappers.<AppRunning>query().lambda().eq(AppRunning::getAppId, appId));
    }

    @Override
    public void deleteRunningApp(String appId, String clientId) {
        this.remove(Wrappers.<AppRunning>query().lambda().eq(AppRunning::getAppId, appId).eq(AppRunning::getClientId, clientId));
    }

    @Override
    public void createRunningApp(AppRunning appRunning) {
        this.save(appRunning);
    }
}
