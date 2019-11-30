package com.refordom.dev.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.dev.user.dao.DevUserDao;
import com.refordom.dev.user.entity.DevUser;
import com.refordom.dev.user.service.DevUserService;
import org.springframework.stereotype.Service;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/29 10:49
 */
@Service
public class DevUserServiceImpl extends ServiceImpl<DevUserDao, DevUser> implements DevUserService {
}
