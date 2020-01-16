package com.refordom.app.config;

import com.refordom.app.core.AppDetails;
import com.refordom.app.core.AppEnum;
import com.refordom.app.core.validator.ParamBean;

/**
 * 应用处理回话，一般用作过滤器链的上下文处理
 *
 * @author pricess.wang
 * @date 2019/12/26 18:01
 */
public interface AppToken {

    AppDetails getAppDetails();

    String getAccessToken();

    ParamBean getParamBean();
}
