package com.refordom.auth.user;

import com.refordom.common.rpc.user.RpcUserService;
import com.refordom.common.rpc.user.UserServiceAdapter;

/**
 * @author pricess.wang
 * @date 2019/11/29 23:57
 */
public interface UserSource {

    RpcUserService userService();

    UserServiceAdapter userServiceAdapter();

    void config();
}
