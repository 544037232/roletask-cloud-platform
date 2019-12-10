package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppBusinessDao;
import com.refordom.enterprise.app.model.entity.AppBusiness;
import com.refordom.enterprise.app.model.service.AppBusinessService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppBusinessServiceImpl extends ServiceImpl<AppBusinessDao, AppBusiness> implements AppBusinessService {
}
