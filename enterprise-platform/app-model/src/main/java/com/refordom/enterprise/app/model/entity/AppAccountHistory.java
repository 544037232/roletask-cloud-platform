package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.version.AppHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 科目应用历史版本，不存储最新版，当最新版升级后，前一个版本要存到此处
 *
 * @author pricess.wang
 * @date 2019/12/11 10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_account_history")
public class AppAccountHistory extends AppHistory {

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
