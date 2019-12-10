package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.common.data.BaseModel;
import com.refordom.enterprise.app.model.enums.PairEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 科目应用
 *
 * @author pricess.wang
 * @date 2019/12/10 15:01
 */
@EqualsAndHashCode(callSuper = true)
@TableName("app_account")
@Data
@ToString
public class AppAccount extends BaseModel<AppAccount> {

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
     * 科目编码
     */
    private String accountCode;

    /**
     * 科目名称
     */
    private String accountName;

    /**
     * 科目分组名
     */
    private String groupName;

    /**
     * 余额校验
     */
    private Boolean balanceCheck;

    /**
     * 配对方式
     */
    private PairEnum pairingModel;

}
