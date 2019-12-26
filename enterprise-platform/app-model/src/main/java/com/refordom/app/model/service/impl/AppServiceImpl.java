package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.AppDetails;
import com.refordom.app.model.dao.AppDao;
import com.refordom.app.model.entity.App;
import com.refordom.app.model.service.AppService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/26 14:51
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppDao, App> implements AppService {

    @Override
    public AppDetails getApp(String appId) {
        return this.getOne(Wrappers.<App>query().lambda().eq(App::getAppId, appId));
    }

    @Override
    public void createApp(AppDetails appDetails) {
        baseMapper.insert((App) appDetails);
    }

}
