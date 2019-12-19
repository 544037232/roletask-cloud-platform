package com.refordom.app.config.constant;

/**
 * @author pricess.wang
 * @date 2019/12/16 16:46
 */
public interface AppConstant {

    /**
     * 应用过滤器链bean注册名称
     */
    String APP_FILTER_CHAIN_NAME = "appFilterChain";

    /**
     * 应用过滤器链请求地址通配符
     */
    String APP_URL_PATTERNS = "/app/store/*";

    /**
     * 应用ID公共参数
     */
    String PARAM_APP_ID = "app_id";

    /**
     * token参数
     */
    String PARAM_ACCESS_TOKEN = "Authorization";

    /**
     * 应用类型参数
     */
    String PARAM_APP_TYPE = "app_type";

}
