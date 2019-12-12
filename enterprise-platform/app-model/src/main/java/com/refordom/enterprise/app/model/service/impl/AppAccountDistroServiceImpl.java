package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppAccountDistroDao;
import com.refordom.enterprise.app.model.entity.AppAccountDistro;
import com.refordom.enterprise.app.model.service.AppAccountDistroService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppAccountDistroServiceImpl extends ServiceImpl<AppAccountDistroDao, AppAccountDistro> implements AppAccountDistroService {
}