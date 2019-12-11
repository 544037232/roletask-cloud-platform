package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.version.AppRunning;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务应用在各个客户环境运行的版本
 *
 * @author pricess.wang
 * @date 2019/12/11 10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_business_running")
public class AppBusinessRunning extends AppRunning {

    /**
     * 应用关联key，用于关联外部标识
     */
    private String appKey;

    /**
     * 应用名称
     */
    private String appName;

    @TableField(exist = false)
    private AppBusiness appBusiness;

}
