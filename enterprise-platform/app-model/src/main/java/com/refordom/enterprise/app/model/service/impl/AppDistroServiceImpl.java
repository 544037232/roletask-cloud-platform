package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppDistroDao;
import com.refordom.enterprise.app.model.service.AppDistroService;
import com.refordom.enterprise.app.model.entity.AppDistro;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppDistroServiceImpl extends ServiceImpl<AppDistroDao, AppDistro> implements AppDistroService {
}
