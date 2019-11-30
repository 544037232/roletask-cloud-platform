package com.refordom.dev.user.rpc;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.refordom.dev.user.api.DevUserInfo;
import com.refordom.dev.user.api.IDevUserService;
import com.refordom.dev.user.entity.DevUser;
import com.refordom.dev.user.service.DevUserService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * <p>用户远程RPC接口 </p>
 *
 * @author pricess.wang
 * @date 2019/9/18 17:16
 */
@Service(group = "dev",version = "1.0.0.0")
public class IDevUserServiceImpl implements IDevUserService {

    @Resource
    private DevUserService devUserService;

    @Override
    public DevUserInfo getByUsername(String username) {

        DevUser user = devUserService.getOne(Wrappers.<DevUser>query()
                .lambda().eq(DevUser::getNickname, username));
        return userInfoAdapter(user);
    }

    @Override
    public DevUserInfo getByMobile(String mobile) {
        DevUser user = devUserService.getOne(Wrappers.<DevUser>query()
                .lambda().eq(DevUser::getMobile, mobile));

        return userInfoAdapter(user);
    }

    private DevUserInfo userInfoAdapter(DevUser user) {

        if (null == user) {
            return null;
        }
        DevUserInfo userInfo = new DevUserInfo();

        BeanUtil.copyProperties(user, userInfo);

        return userInfo;
    }
}
