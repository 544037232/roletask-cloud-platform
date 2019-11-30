package com.refordom.auth.user;

import com.refordom.common.rpc.user.RpcUserService;

/**
 * @author pricess.wang
 * @date 2019/11/29 23:57
 */
public interface UserSource {

    RpcUserService userService();

    void config();
}
