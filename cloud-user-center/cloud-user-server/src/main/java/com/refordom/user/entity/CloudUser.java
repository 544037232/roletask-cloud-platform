package com.refordom.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import com.refordom.user.api.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * <p>用户云属性，本平台通用信息</p>
 *
 * @author pricess.wang
 * @date 2019/12/7 11:06
 */
@EqualsAndHashCode(callSuper = true)
@TableName("cloud_user")
@Data
public class CloudUser extends BaseModel<CloudUser> implements UserInfo {

    @TableId
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
     * @see BCryptPasswordEncoder
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
    @TableField(exist = false)
    private List<Long> roles;

    /**
     * 权限
     */
    @TableField(exist = false)
    private List<String> permissions;

}
