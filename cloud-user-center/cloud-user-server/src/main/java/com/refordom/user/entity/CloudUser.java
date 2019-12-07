package com.refordom.user.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.user.api.UserInfo;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>用户云属性，本平台通用信息</p>
 *
 * @author pricess.wang
 * @date 2019/12/7 11:06
 */
@TableName("cloud_user")
@Data
public class CloudUser implements UserInfo {

    @TableId
    private Long id;

    /**
     * 用户显示名
     */
    private String nickname;

    /**
     * 工号，全表唯一
     */
    private String jobNumber;

    /**
     * @see BCryptPasswordEncoder
     * 密码
     */
    private String password;

    /**
     * 用户手机号，全表唯一
     */
    private String mobile;

    /**
     * 邮箱，用户接受验证码等
     */
    private String email;

    /**
     * 头像，可为空
     */
    private String avatar;

}
