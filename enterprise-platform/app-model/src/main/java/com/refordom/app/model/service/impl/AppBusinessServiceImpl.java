package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.AppModel;
import com.refordom.app.model.dao.AppBusinessDao;
import com.refordom.app.model.entity.AppBusiness;
import com.refordom.app.model.service.AppBusinessService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppBusinessServiceImpl extends ServiceImpl<AppBusinessDao, AppBusiness> implements AppBusinessService {
    @Override
    public AppModel getModel(String appId) {
        return baseMapper.selectOne(Wrappers.<AppBusiness>query().lambda().eq(AppBusiness::getAppId, appId));
    }

    @Override
    public void createModel(AppModel appModel) {
        baseMapper.insert((AppBusiness) appModel);
    }
}
