package com.refordom.auth.service;

import com.refordom.auth.user.UserServiceFactory;
import com.refordom.common.rpc.user.UserInfo;
import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.common.security.util.ClientUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
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

    @Resource
    private UserServiceFactory userServiceFactory;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String[] tokens = ClientUtils.getClientInfo(request);

        AuthClientDetails authClientDetails = (AuthClientDetails) clientDetailsService.loadClientByClientId(tokens[0]);

        UserInfo userInfo = userServiceFactory.getRpcService(authClientDetails.getAuthTarget()).getByUsername(username);

        if (null == userInfo) {
            throw new UsernameNotFoundException("用户名不存在.");
        }
        return userServiceFactory.getAdapter(authClientDetails.getAuthTarget()).adapter(userInfo);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        String[] tokens = ClientUtils.getClientInfo(request);

        AuthClientDetails authClientDetails = (AuthClientDetails) clientDetailsService.loadClientByClientId(tokens[0]);

        UserInfo userInfo = userServiceFactory.getRpcService(authClientDetails.getAuthTarget()).getByMobile(mobile);

        if (null == userInfo) {
            throw new UsernameNotFoundException("未找到手机号:" + mobile + " 的用户信息.");
        }
        return userServiceFactory.getAdapter(authClientDetails.getAuthTarget()).adapter(userInfo);
    }

}
