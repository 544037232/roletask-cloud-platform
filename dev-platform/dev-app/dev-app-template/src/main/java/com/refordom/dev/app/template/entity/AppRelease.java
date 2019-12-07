package com.refordom.dev.app.template.entity;

import com.refordom.common.data.BaseModel;

/**
 * <p>应用发布</p>
 *
 * @author pricess.wang
 * @date 2019/12/7 18:13
 */
public class AppRelease extends BaseModel<AppRelease> {

    /**
     * 应用模板
     */
    private AppTemplate appTemplate;

    /**
     * 发布类型，1表示授权给私有客户，2表示发布到应用商店
     */
    private Integer releaseType;

    /**
     * 版本发布说明
     */
    private String editionDescription;

    /**
     * 版本号，版本号由应用商店生成，生成后返回到开发者平台会写版本号
     */
    private String version;

}
