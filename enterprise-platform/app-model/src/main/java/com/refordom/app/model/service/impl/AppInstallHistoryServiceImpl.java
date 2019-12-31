package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppInstallHistoryDao;
import com.refordom.app.model.entity.AppInstallHistory;
import com.refordom.app.model.entity.AppRunning;
import com.refordom.app.model.service.AppInstallHistoryService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppInstallHistoryServiceImpl extends ServiceImpl<AppInstallHistoryDao, AppInstallHistory> implements AppInstallHistoryService {

    @Override
    public AppInstallHistory getHistoryInstallApp(String appId, String clientId) {
        return this.getOne(Wrappers.<AppInstallHistory>query().lambda().eq(AppInstallHistory::getAppId, appId));
    }

    @Override
    public void createHistoryInstallApp(AppInstallHistory appInstallHistory) {
        this.save(appInstallHistory);
    }
}
