package com.refordom.common.security.constant;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:39
 */
public interface SecurityConstants {

    /**
     * spring security默认的登录接口，本项目弃用该入口，使用自定义实现
     */
    String SPRING_SECURITY_OAUTH_TOKEN = "/oauth/token";

    /**
     * 表单登录默认认证接口
     */
    String DEFAULT_AUTH_LOGIN_FORM_ACCESS_TOKEN = "/login/form/access_token";

    /**
     * 表单登录默认请求地址
     */
    String DEFAULT_AUTH_LOGIN_PAGE_ACCESS_TOKEN = "/login/form/page";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_AUTH_LOGIN_MOBILE_ACCESS_TOKEN = "/login/mobile";

    /**
     * 默认的oauth2协议登录请求地址
     */
    String DEFAULT_AUTH_LOGIN_OAUTH2_ACCESS_TOKEN = "/login/oauth2/authorize";

    /**
     * 默认的OPENID登录请求处理url
     */
    String DEFAULT_SIGN_IN_LOGIN_OPENID = "/login/openid";

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 默认的验证码参数
     */
    String DEFAULT_VALIDATE_CODE_PARAMETER = "code";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 默认校验码唯一随机数
     */
    String DEFAULT_VALIDATE_PARAMETER_NAME_KEY_RANDOM = "randomStr";
    /**
     * 默认的登录页面
     */
    String DEFAULT_SIGN_IN_LOGIN_URL = "/ftl/login.ftl";

    /**
     * 默认的注册页面
     */
    String DEFAULT_SIGN_IN_SIGN_UP_URL = "/sign-up.html";

    /**
     * 默认的session失效请求地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/session-invalid.html";

    /**
     * basic请求头前缀
     */
    String BASIC_PREFIX = "Basic ";

    /**
     * 默认token类型请求头前缀
     */
    String DEFAULT_HEADER_TOKEN_TYPE_PREFIX = "bearer ";

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /**
     * 第三方登录
     */
    String OAUTH = "/oauth";

}
