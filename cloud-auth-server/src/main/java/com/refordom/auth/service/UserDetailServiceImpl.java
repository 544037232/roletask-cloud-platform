package com.refordom.auth.service;

import com.refordom.auth.user.UserDefaultSource;
import com.refordom.common.rpc.user.UserInfo;
import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.common.security.util.ClientUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>获取用户详细信息</p>
 *
 * @author pricess.wang
 * @date 2019/9/16 17:01
 */
@Component
public class UserDetailServiceImpl implements SecurityUserDetailsService {

    @Resource
    private HttpServletRequest request; //自动注入request

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String[] tokens = ClientUtils.getClientInfo(request);

        UserInfo devUserInfo = UserDefaultSource.DEV_USER.userService().getByUsername(username);

        if (null == devUserInfo) {
            throw new UsernameNotFoundException("用户名不存在.");
        }
        return null;
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        String[] tokens = ClientUtils.getClientInfo(request);


        UserInfo userInfo =  UserDefaultSource.DEV_USER.userService().getByMobile(mobile);

        if (null == userInfo) {
            throw new UsernameNotFoundException("未找到手机号:" + mobile + " 的用户信息.");
        }
        return null;
    }

}
