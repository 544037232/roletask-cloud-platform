package com.refordom.auth.user;

import com.refordom.common.rpc.user.UserInfo;

/**
 * @author pricess.wang
 * @date 2019/11/30 0:06
 */
public abstract class DefaultUserRequest implements UserRequest {

    private UserSource userSource;

    public DefaultUserRequest(UserSource userSource){
        userSource.config();
        this.userSource = userSource;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userSource.userService().getByUsername(username);
    }

    @Override
    public UserInfo getUserInfoByMobile(String mobile) {
        return userSource.userService().getByMobile(mobile);
    }

}
