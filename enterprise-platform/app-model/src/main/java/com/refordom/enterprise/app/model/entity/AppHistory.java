package com.refordom.enterprise.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.enterprise.app.model.App;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用历史,记录应用发布和客户端下载运行的历史
 *
 * @author pricess.wang
 * @date 2019/12/10 19:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_history")
public class AppHistory extends App {

    /**
     * 历史版本是否启用用于版本回退，只有可回退的版本才可以在运行环境还原历史版本
     */
    private Boolean enable;
}