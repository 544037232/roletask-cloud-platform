package com.refordom.dev.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>开发者</p>
 *
 * @author pricess.wang
 * @date 2019/11/26 15:41
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dev_user")
@Data
public class DevUser extends BaseModel<DevUser> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 认证类型，1为个人开发者，2为企业认证
     */
    private Integer certificationType;

    /**
     * 状态，0已禁用，1已认证，-1未认证
     */
    private Integer status;

}
