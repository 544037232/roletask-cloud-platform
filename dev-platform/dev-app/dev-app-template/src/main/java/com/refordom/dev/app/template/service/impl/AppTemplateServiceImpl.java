package com.refordom.dev.app.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.dev.app.template.dao.AppTemplateDao;
import com.refordom.dev.app.template.entity.AppTemplate;
import com.refordom.dev.app.template.service.AppTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/3 11:40
 */
@Service
public class AppTemplateServiceImpl extends ServiceImpl<AppTemplateDao, AppTemplate> implements AppTemplateService {

    @Override
    public void release(String appId) {

    }
}
