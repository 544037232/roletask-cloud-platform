package com.refordom.dev.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户认证</p>
 *
 * @author pricess.wang
 * @date 2019/11/27 10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_certification")
public class Certification extends BaseModel<Certification> {

    private Long id;

    /**
     * 开发者ID
     */
    private Long devUserId;

    /**
     * 认证用户唯一代码,系统生成
     */
    private String code;

    /**
     * 区域，如广东省/深圳市/福田区/梅林街道,必填
     */
    private String area;

    /**
     * 详细地址，如幸福路5号卓悦汇B座1706,必填
     */
    private String address;

    /**
     * 证件的真实姓名,必填
     */
    private String personName;

    /**
     * 证件类型,必填
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
     * 附加证件照片,必填
     */
    private String additionalProfile;

    /**
     * 网站经营许可证编号,非必填
     */
    private String icpNo;

    /**
     * 公司网站,非必填
     */
    private String website;

    /**
     * 联系人姓名,必填
     */
    private String contactName;

    /**
     * 联系人手机号,必填
     */
    private String contactPhone;

    /**
     * 客服电话，用于接收通知，应用上架到应用商店后，对外显示,必填
     */
    private String serviceTel;

    /**
     * 统一社会信用代码,如果是企业，则必填
     */
    private String creditNo;

    /**
     * 营业执照照片,如果是企业，则必填
     */
    private String businessLicenseProfile;

    /**
     * 营业执照有效期,如果是企业，则必填
     */
    private String businessValidRange;

}
