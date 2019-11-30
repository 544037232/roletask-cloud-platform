package com.refordom.auth.user;

import com.refordom.common.rpc.user.UserInfo;

/**
 * @author pricess.wang
 * @date 2019/11/29 23:57
 */
public interface UserRequest {

    UserInfo getUserInfoByUsername(String username);

    UserInfo getUserInfoByMobile(String mobile);
}
