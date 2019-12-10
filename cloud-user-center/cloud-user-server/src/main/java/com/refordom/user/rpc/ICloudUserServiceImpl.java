package com.refordom.user.rpc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.refordom.user.api.IUserService;
import com.refordom.user.api.UserInfo;
import com.refordom.user.entity.*;
import com.refordom.user.service.CloudUserService;
import com.refordom.user.service.CustomerAdminService;
import com.refordom.user.service.DeveloperService;
import com.refordom.user.service.EnterpriseMemberService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private CustomerAdminService customerAdminService;

    @Resource
    private EnterpriseMemberService enterpriseMemberService;

    @Resource
    private DeveloperService developerService;

    @Override
    public UserInfo getByUsername(String username) {
        CloudUser cloudUser = cloudUserService.getOne(Wrappers.<CloudUser>query()
                .lambda().eq(CloudUser::getUsername, username));

        List<UserIdentity> userIdentities = new ArrayList<>();

        userIdentities.add(developer(username));
        userIdentities.add(customerAdmin(username));
        userIdentities.add(enterpriseMember(username));

        cloudUser.setIdentities(userIdentities);

        return cloudUser.userInfoAdapter(cloudUser);
    }

    @Override
    public UserInfo getByPhone(String phone) {
        CloudUser cloudUser = cloudUserService.getOne(Wrappers.<CloudUser>query()
                .lambda().eq(CloudUser::getPhone, phone));
        return cloudUser.userInfoAdapter(cloudUser);
    }

    private Developer developer(String username) {
        return developerService.getOne(Wrappers.<Developer>query()
                .lambda().eq(Developer::getUsername, username));
    }

    private CustomerAdmin customerAdmin(String username) {
        return customerAdminService.getOne(Wrappers.<CustomerAdmin>query()
                .lambda().eq(CustomerAdmin::getUsername, username));
    }

    private EnterpriseMember enterpriseMember(String username) {
        return enterpriseMemberService.getOne(Wrappers.<EnterpriseMember>query()
                .lambda().eq(EnterpriseMember::getUsername, username));
    }

}
