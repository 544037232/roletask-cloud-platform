package com.refordom.user.api;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/11/29 20:46
 */
@Builder
@Data
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户显示名
     */
    private String nickname;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户手机号，全表唯一
     */
    private String phone;

    /**
     * 邮箱，用户接受验证码等
     */
    private String email;

    /**
     * 头像，可为空
     */
    private String avatar;

    /**
     * 删除标识，true为删除，false为正常
     */
    private Boolean delFlag;

    /**
     * 角色
     */
    private List<String> roles;

    /**
     * 权限
     */
    private List<String> permissions;

}
