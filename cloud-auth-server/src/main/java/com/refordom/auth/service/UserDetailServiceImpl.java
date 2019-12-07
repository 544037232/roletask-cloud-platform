package com.refordom.auth.service;

import cn.hutool.core.util.ArrayUtil;
import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.model.AuthUserDetail;
import com.refordom.user.api.IUserService;
import com.refordom.user.api.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 * <p>获取用户详细信息</p>
 *
 * @author pricess.wang
 * @date 2019/9/16 17:01
 */
@Component
public class UserDetailServiceImpl implements SecurityUserDetailsService {

    @Reference(group = "user",version = "1.0.0.0")
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
        Set<String> authorities = new HashSet<>();

        if (ArrayUtil.isNotEmpty(userInfo.getRoles())) {
            // 角色
            userInfo.getRoles().forEach(roleId -> authorities.add(SecurityConstants.ROLE_PREFIX + roleId));
            // 资源权限
            authorities.addAll(userInfo.getPermissions());
        }

        return new AuthUserDetail(
                userInfo.getId(),
                userInfo.getNickname(),
                userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getPhone(),
                userInfo.getEmail(),
                userInfo.getAvatar(),
                !userInfo.getDelFlag(),
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))
        );
    }
}
