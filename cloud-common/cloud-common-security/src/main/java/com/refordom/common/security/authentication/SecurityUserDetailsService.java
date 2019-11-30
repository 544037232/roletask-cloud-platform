package com.refordom.common.security.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 默认的 UserDetailsService 实现
 * 其他模块使用必须重写loadUserByUsername方法
 *
 * @author : 王晟权
 * @date : 2019/6/12 17:50
 */
public interface SecurityUserDetailsService extends UserDetailsService {

    /**
     * 根据用户名获取用户详情和角色权限信息
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("请重写loadUserByUsername接口");
    }

    /**
     * 根据手机号获取用户详情和解决权限信息
     *
     * @param mobile 手机号
     * @return UserDetails
     * @throws UsernameNotFoundException 用户名不存在
     */
    default UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("请重写loadUserByMobile接口");
    }

}
