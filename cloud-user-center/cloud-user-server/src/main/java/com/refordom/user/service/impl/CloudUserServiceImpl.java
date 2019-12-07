package com.refordom.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.user.dao.CloudUserDao;
import com.refordom.user.entity.CloudUser;
import com.refordom.user.service.CloudUserService;
import org.springframework.stereotype.Service;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/29 10:49
 */
@Service
public class CloudUserServiceImpl extends ServiceImpl<CloudUserDao, CloudUser> implements CloudUserService {
}
