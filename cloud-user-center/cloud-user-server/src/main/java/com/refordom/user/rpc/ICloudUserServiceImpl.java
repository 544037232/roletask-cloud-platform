package com.refordom.user.rpc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.refordom.user.api.IUserService;
import com.refordom.user.api.UserInfo;
import com.refordom.user.entity.CloudUser;
import com.refordom.user.service.CloudUserService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * <p>用户远程RPC接口 </p>
 *
 * @author pricess.wang
 * @date 2019/9/18 17:16
 */
@Service(group = "user", version = "1.0.0.0")
public class ICloudUserServiceImpl implements IUserService {

    @Resource
    private CloudUserService cloudUserService;

    @Override
    public UserInfo getByUsername(String username) {
        CloudUser cloudUser = cloudUserService.getOne(Wrappers.<CloudUser>query()
                .lambda().eq(CloudUser::getUsername, username));

        return cloudUser.userInfoAdapter(cloudUser);
    }

    @Override
    public UserInfo getByPhone(String phone) {
        CloudUser cloudUser = cloudUserService.getOne(Wrappers.<CloudUser>query()
                .lambda().eq(CloudUser::getPhone, phone));
        return cloudUser.userInfoAdapter(cloudUser);
    }

}
