package com.refordom.dev.app.template.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用模板
 * @author pricess.wang
 * @date 2019/12/3 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("app_template")
public class AppTemplate extends BaseModel<AppTemplate> {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 描述
     */
    private String description;

    /**
     * 所属者，创建时默认属于企业认证开发者，此后将永远不能更改，发布到应用商店，
     * 所属者将会展示该企业认证开发信息，即使别的开发认证下载该应用进行二次开发，所属也仍然不变
     */
    private Long owner;

}
