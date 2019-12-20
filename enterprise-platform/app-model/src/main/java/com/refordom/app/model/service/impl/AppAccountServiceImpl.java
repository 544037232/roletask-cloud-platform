package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.AppModel;
import com.refordom.app.model.entity.AppAccount;
import com.refordom.app.model.dao.AppAccountDao;
import com.refordom.app.model.service.AppAccountService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppAccountServiceImpl extends ServiceImpl<AppAccountDao, AppAccount> implements AppAccountService {

    @Override
    public AppModel getModel(String appId) {
        return baseMapper.selectOne(Wrappers.<AppAccount>query().lambda().eq(AppAccount::getAppId, appId));
    }

    @Override
    public void createModel(AppModel appModel) {
        baseMapper.insert((AppAccount) appModel);
    }
}
