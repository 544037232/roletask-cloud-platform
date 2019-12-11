package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.version.AppHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务应用的历史版本
 *
 * @author pricess.wang
 * @date 2019/12/11 10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_business_history")
public class AppBusinessHistory extends AppHistory {

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
