package com.refordom.dev.user.api;

import com.refordom.common.rpc.user.UserInfo;
import com.refordom.common.rpc.user.UserServiceAdapter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author pricess.wang
 * @date 2019/12/2 19:25
 */
public class DevUserDetailsAdapter implements UserServiceAdapter {

    @Override
    public UserDetails adapter(UserInfo userInfo) {
        DevUserInfo devUserInfo = (DevUserInfo) userInfo;
        return new DevUserDetails(devUserInfo);
    }
}
