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
     * 应用事务管理器
     */
    String APP_TRANSACTION_MANAGER = "appTransactionManager";
}
