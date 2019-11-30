package com.refordom.common.security.constant;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:39
 */
public interface SecurityConstants {

    /**
     * spring security默认的token接口
     */
    String SPRING_SECURITY_OAUTH_TOKEN = "/oauth/token";
    /**
     * 默认登录请求表单接口
     */
    String DEFAULT_SIGN_IN_LOGIN_FORM = "/token/form";

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_AUTHENTICATION_URL = "/token/login";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/oauth/mobile";

    /**
     * 默认的OPENID登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/oauth/openid";

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
