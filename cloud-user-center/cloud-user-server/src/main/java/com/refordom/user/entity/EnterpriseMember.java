package com.refordom.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业成员
 *
 * @author pricess.wang
 * @date 2019/12/9 17:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_identity_enterprise")
public class EnterpriseMember extends AbstractUserIdentity {

    /**
     * 别名
     */
    private String alias;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别，1为男，2为女
     */
    private Integer gender;

    @Override
    public String getPrefix() {
        return "EM";
    }
}
