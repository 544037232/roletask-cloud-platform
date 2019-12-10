package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppAmbientDao;
import com.refordom.enterprise.app.model.entity.AppAmbient;
import com.refordom.enterprise.app.model.service.AppAmbientService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppAmbientServiceImpl extends ServiceImpl<AppAmbientDao, AppAmbient> implements AppAmbientService {
}
