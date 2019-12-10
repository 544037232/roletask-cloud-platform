package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppAccountDao;
import com.refordom.enterprise.app.model.entity.AppAccount;
import com.refordom.enterprise.app.model.service.AppAccountService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppAccountServiceImpl extends ServiceImpl<AppAccountDao, AppAccount> implements AppAccountService {
}
