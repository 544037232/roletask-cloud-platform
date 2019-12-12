package com.refordom.enterprise.app.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.refordom.common.data.BaseModel;
import com.refordom.enterprise.app.model.enums.AppEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 应用公共属性
 *
 * @author pricess.wang
 * @date 2019/12/10 19:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class App extends BaseModel<App> {

    @TableId
    private Long id;

    /**
     * 本次编译的唯一Key Id
     */
    private String kid;

    /**
     * 应用ID，业务应用或科目应用
     */
    private String appId;

    /**
     * 应用key，用于关联业务的唯一标识
     */
    private String appKey;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 运算规则签名，此签名为jwt
     */
    private String sign;

    /**
     * 签发者，即开发者所属名称
     */
    private String issuer;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 发布日期
     */
    private Date distroDate;

    /**
     * 变更日志，当前版本相对于上一版本变更的升级说明
     */
    private String changeLog;

    /**
     * 版本号，根据应用商店发行版本，部署范围（客户二次开发私有还是应用商店公有）综合计算得到
     */
    private String version;

    /**
     * 互斥版本，记录过去的版本用于限制版本回退，防止不兼容导致应用异常，不可存储此版本之后的版本
     */
    private String mutexVersion;

    /**
     * AppEnum
     */
    @TableField(value = "app_type")
    private AppEnum appType;

    /**
     * 应用模型
     */
    @TableField(exist = false)
    private AppModel appModel;
}
