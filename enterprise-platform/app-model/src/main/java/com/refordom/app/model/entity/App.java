package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.app.core.AppDetails;
import com.refordom.app.core.AppEnum;
import com.refordom.common.data.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author pricess.wang
 * @date 2019/12/26 14:47
 */
@EqualsAndHashCode(callSuper = true)
@TableName("app")
@Data
@ToString
@Builder
public class App extends BaseModel<App> implements AppDetails {

    @TableId
    private Long id;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 应用logo
     */
    private String logo;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用类型
     */
    private AppEnum appType;

    /**
     * 应用说明
     */
    private String appExplain;

    /**
     * 应用所属
     */
    private String owner;
}
