package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.app.model.AppBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用定制发行版，客户自定义专属的版本，与应用商店发行版隔离
 *
 * @author pricess.wang
 * @date 2019/12/10 19:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_made_distro")
public class AppMadeDistro extends AppBasic {

    /**
     * 应用是否上架，只有上架的应用才可以下载
     */
    private Boolean shelves;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 定制打包的版本号，与应用商店隔离版本
     */
    private String madeVersion;
}
