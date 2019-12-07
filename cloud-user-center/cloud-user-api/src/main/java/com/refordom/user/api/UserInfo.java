package com.refordom.user.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/11/29 20:46
 */
public interface UserInfo extends Serializable {

    /**
     * 获取用户唯一ID
     *
     * @return ID
     */
    Long getId();

    /**
     * 获取用户名称
     *
     * @return nickname
     */
    String getNickname();

    /**
     * 获取用户名
     *
     * @return jobNumber
     */
    String getUsername();

    /**
     * 获取用户已加密的密码
     *
     * @return password
     */
    String getPassword();

    /**
     * 获取用户手机号
     *
     * @return phone
     */
    String getPhone();

    /**
     * 获取用户邮箱
     *
     * @return email
     */
    String getEmail();

    /**
     * 获取用户头像
     *
     * @return avatar
     */
    String getAvatar();

    /**
     * 删除表示
     *
     * @return delFlag
     */
    Boolean getDelFlag();

    /**
     * 获取权限
     * @return permission
     */
    List<String> getPermissions();

    /**
     * 获取角色
     * @return role
     */
    List<Long> getRoles();
}
