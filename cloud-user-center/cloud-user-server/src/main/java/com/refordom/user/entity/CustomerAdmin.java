package com.refordom.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户管理员
 *
 * @author pricess.wang
 * @date 2019/12/9 17:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_identity_customer")
@Data
public class CustomerAdmin extends AbstractUserIdentity {

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 办公邮件
     */
    private String officeEmail;

    /**
     * 办公座机
     */
    private String officeTel;

    @Override
    public String getPrefix() {
        return "CA";
    }
}
