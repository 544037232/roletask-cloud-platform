package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 客户应用环境许可
 *
 * @author pricess.wang
 * @date 2019/12/11 18:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_ambient_licence ")
public class AppAmbientLicence extends BaseModel<AppAmbientLicence> {

    private Long id;

    /**
     * 客户端唯一ID
     */
    private String clientId;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 过期时间
     */
    private Date expiresAt;

    /**
     * 许可证
     */
    private String licenceKey;

}
