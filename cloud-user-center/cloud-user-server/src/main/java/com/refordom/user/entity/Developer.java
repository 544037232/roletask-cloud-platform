package com.refordom.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开发者
 *
 * @author pricess.wang
 * @date 2019/12/9 17:30
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_identity_developer")
@Data
public class Developer extends AbstractUserIdentity {
    /**
     * 证件的真实姓名,必填
     */
    private String personName;

    /**
     * 证件类型,如身份证，必填
     */
    private String papersType;

    /**
     * 证件号码,必填
     */
    private String papersNo;

    /**
     * 证件有效期范围,必填
     */
    private String paperValidRange;

    /**
     * 证件正面照片,必填
     */
    private String paperProfileFront;

    /**
     * 证件反面照片,必填
     */
    private String paperProfileBack;

    /**
     * 手持证件照片,必填
     */
    private String paperProfileHold;

    /**
     * 附加证件照片,非必填
     */
    private String additionalProfile;

    @Override
    public String getPrefix() {
        return "DEV";
    }
}
