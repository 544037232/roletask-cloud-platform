package com.refordom.app.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.refordom.app.model.AppBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 应用发行版，应用商店唯一的最新版本
 *
 * @author pricess.wang
 * @date 2019/12/10 19:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@TableName("app_distro")
public class AppDistro extends AppBasic {

    /**
     * 应用是否上架，只有上架的应用才可以下载
     */
    private Boolean shelves;

}
