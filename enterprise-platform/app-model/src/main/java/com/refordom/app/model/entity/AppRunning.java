package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.app.model.App;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 不同客户运行中的应用版本
 *
 * @author pricess.wang
 * @date 2019/12/10 19:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_running")
public class AppRunning extends App {

    /**
     * 过期时间，如果过期，则签名规则不可用于业务执行
     */
    private Date expiresAt;

    /**
     * 客户端环境ID
     */
    private String clientId;

}
