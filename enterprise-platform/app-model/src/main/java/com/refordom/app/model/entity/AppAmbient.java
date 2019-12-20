package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import com.refordom.app.model.AppModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 客户应用环境
 *
 * @author pricess.wang
 * @date 2019/12/9 16:13
 */
@EqualsAndHashCode(callSuper = true)
@TableName("app_ambient")
@Data
@ToString
public class AppAmbient extends BaseModel<AppAmbient> {

    @TableId
    private Long id;

    /**
     * 客户端唯一编码
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 环境域名
     */
    private String domain;
}
