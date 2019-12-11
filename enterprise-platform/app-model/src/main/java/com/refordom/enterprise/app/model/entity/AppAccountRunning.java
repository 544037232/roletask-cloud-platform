package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.version.AppRunning;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 科目应用在各个客户运行的版本,方便推送版本更新通知
 *
 * @author pricess.wang
 * @date 2019/12/11 10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_account_running")
public class AppAccountRunning extends AppRunning {

    /**
     * 科目编码
     */
    private String accountCode;

    /**
     * 科目名称
     */
    private String accountName;

    @TableField(exist = false)
    private AppAccount appAccount;
}
