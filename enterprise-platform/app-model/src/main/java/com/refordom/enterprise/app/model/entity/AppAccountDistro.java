package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.version.AppDistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 科目应用发布版本（应用商店展示的最新版）
 *
 * @author pricess.wang
 * @date 2019/12/11 10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_account_distro")
public class AppAccountDistro extends AppDistro {

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
