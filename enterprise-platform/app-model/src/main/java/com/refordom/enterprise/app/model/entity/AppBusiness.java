package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import com.refordom.enterprise.app.model.AppModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务应用
 *
 * @author pricess.wang
 * @date 2019/12/10 11:03
 */
@EqualsAndHashCode(callSuper = true)
@TableName("app_business")
@Data
@ToString
public class AppBusiness extends BaseModel<AppBusiness> implements AppModel {

    @TableId
    private Long id;

    /**
     * 应用唯一ID
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 应用关联key，用于关联外部标识
     */
    private String appKey;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 应用所有者，如果是企业开发团队，则所属者就是团队账号名
     */
    private String owner;

}
