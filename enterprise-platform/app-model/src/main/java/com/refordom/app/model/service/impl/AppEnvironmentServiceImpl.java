package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppEnvironmentDao;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.service.AppEnvironmentService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppEnvironmentServiceImpl extends ServiceImpl<AppEnvironmentDao, AppEnvironment> implements AppEnvironmentService {
}
