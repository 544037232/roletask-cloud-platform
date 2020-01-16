package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.exception.ConcurrentException;
import com.refordom.app.model.service.AppDistroService;
import com.refordom.app.model.dao.AppDistroDao;
import com.refordom.app.model.entity.AppDistro;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppDistroServiceImpl extends ServiceImpl<AppDistroDao, AppDistro> implements AppDistroService {

    @Override
    public AppDistro getDistroApp(String appId) {
        return this.getOne(Wrappers.<AppDistro>query().lambda().eq(AppDistro::getAppId, appId));
    }

    @Override
    public void updateDistroAppByAppId(AppDistro appDistro) {
        boolean updateFlag = this.update(appDistro, Wrappers.<AppDistro>update().lambda()
                .eq(AppDistro::getShelves, false)
                .eq(AppDistro::getAppId, appDistro.getAppId()));

        if (!updateFlag) {
            throw new ConcurrentException("应用已经上架");
        }
    }

    @Override
    public void createDistroApp(AppDistro appDistro) {
        this.save(appDistro);
    }
}
