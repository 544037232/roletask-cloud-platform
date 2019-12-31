package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.app.model.service.AppHistoryService;
import com.refordom.app.model.dao.AppHistoryDao;
import com.refordom.app.model.entity.AppHistory;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppHistoryServiceImpl extends ServiceImpl<AppHistoryDao, AppHistory> implements AppHistoryService {

    @Override
    public AppHistory getHistoryApp(String appId) {
        return this.getOne(Wrappers.<AppHistory>query().lambda().eq(AppHistory::getAppId, appId));
    }

    @Override
    public void createHistoryApp(AppHistory appHistory) {
        this.save(appHistory);
    }
}
