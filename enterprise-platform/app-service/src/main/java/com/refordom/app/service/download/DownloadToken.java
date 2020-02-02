package com.refordom.app.service.download;

import com.refordom.app.core.AppEnum;
import com.refordom.common.action.builder.ResultToken;
import lombok.Data;

/**
 * <p>下载回调信息</p>
 *
 * @author pricess.wang
 * @date 2020/1/31 18:07
 */
@Data
public class DownloadToken implements ResultToken {

    // 应用ID
    private String appId;

    // 应用类型
    private AppEnum appType;

    // 下载的内容
    private String sign;

    // 版本号
    private String version;

    // 版权唯一ID
    private String licenceKey;

}
