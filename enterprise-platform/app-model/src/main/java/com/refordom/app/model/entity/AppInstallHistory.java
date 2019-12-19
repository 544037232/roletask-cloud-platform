package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.app.model.App;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用安装历史，记录客户端安装的历史版本数据
 *
 * @author pricess.wang
 * @date 2019/12/10 19:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_install_history")
public class AppInstallHistory extends App {

    /**
     * 历史版本是否启用用于版本回退，只有可回退的版本才可以在运行环境还原历史版本
     */
    private Boolean enable;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 定制打包的版本号，与应用商店隔离版本
     */
    private String madeVersion;

}
