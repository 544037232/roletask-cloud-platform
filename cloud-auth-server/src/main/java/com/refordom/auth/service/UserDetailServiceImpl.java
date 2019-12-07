package com.refordom.auth.service;

import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.user.api.IUserService;
import com.refordom.user.api.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * <p>获取用户详细信息</p>
 *
 * @author pricess.wang
 * @date 2019/9/16 17:01
 */
@Component
public class UserDetailServiceImpl implements SecurityUserDetailsService {

    @Reference
    private IUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserInfo userInfo = userService.getByUsername(username);

        if (null == userInfo) {
            throw new UsernameNotFoundException("用户名不存在.");
        }
        return userDetailAdapter(userInfo);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserInfo userInfo = userService.getByMobile(mobile);

        if (null == userInfo) {
            throw new UsernameNotFoundException("未找到手机号:" + mobile + " 的用户信息.");
        }
        return userDetailAdapter(userInfo);
    }

    private UserDetails userDetailAdapter(UserInfo userInfo) {
        return null;
    }
}
