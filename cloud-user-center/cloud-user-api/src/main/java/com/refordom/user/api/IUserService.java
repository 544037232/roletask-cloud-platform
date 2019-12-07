package com.refordom.user.api;

/**
 * <p>远程API</p>
 *
 * @author pricess.wang
 * @date 2019/12/7 11:02
 */
public interface IUserService {

    /**
     * 根据用户名获取用户详情
     *
     * @param username 用户名
     * @return T
     */
    UserInfo getByUsername(String username);

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile 手机号
     * @return T
     */
    UserInfo getByMobile(String mobile);
}
