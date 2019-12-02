package com.refordom.common.rpc.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author pricess.wang
 * @date 2019/12/2 19:12
 */
public interface UserServiceAdapter {

    UserDetails adapter(UserInfo userInfo);
}
