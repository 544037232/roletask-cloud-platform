package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppRunningDao;
import com.refordom.app.model.service.AppRunningService;
import com.refordom.app.model.entity.AppRunning;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppRunningServiceImpl extends ServiceImpl<AppRunningDao, AppRunning> implements AppRunningService {
}
