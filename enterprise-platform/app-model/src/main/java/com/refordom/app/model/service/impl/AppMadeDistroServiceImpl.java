package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppMadeDistroDao;
import com.refordom.app.model.entity.AppInstallHistory;
import com.refordom.app.model.entity.AppMadeDistro;
import com.refordom.app.model.entity.AppRunning;
import com.refordom.app.model.service.AppMadeDistroService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppMadeDistroServiceImpl extends ServiceImpl<AppMadeDistroDao, AppMadeDistro> implements AppMadeDistroService {

    @Override
    public AppMadeDistro getMadeDistroApp(String appId, String clientId) {
        return this.getOne(Wrappers.<AppMadeDistro>query().lambda().eq(AppMadeDistro::getAppId, appId));
    }

    @Override
    public void updateMadeDistroAppByAppId(AppMadeDistro appMadeDistro) {
        this.update(Wrappers.<AppMadeDistro>query()
                .lambda()
                .eq(AppMadeDistro::getAppId, appMadeDistro.getAppId())
                .eq(AppMadeDistro::getClientId, appMadeDistro.getClientId()));
    }

    @Override
    public void createMadeDistroApp(AppMadeDistro appMadeDistro) {
        this.save(appMadeDistro);
    }
}
