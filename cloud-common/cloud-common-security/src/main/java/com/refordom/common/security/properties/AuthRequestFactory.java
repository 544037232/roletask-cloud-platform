package com.refordom.common.security.properties;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * AuthRequest工厂类
 * </p>
 *
 * @author 王晟权
 */
public class AuthRequestFactory {
    private final SecurityProperties properties;
    private final AuthStateCache authStateCache;

    public AuthRequestFactory(SecurityProperties properties, AuthStateCache authStateCache) {
        this.properties = properties;
        this.authStateCache = authStateCache;
    }

    /**
     * 返回当前Oauth列表
     *
     * @return Oauth列表
     */
    public List<String> oauthList() {
        return properties.getSocial().getType().keySet().stream().map(Enum::name).collect(Collectors.toList());
    }

    /**
     * 返回AuthRequest对象
     *
     * @param key 获取{@link AuthSource}的key
     * @return {@link AuthRequest}
     */
    public AuthRequest get(String key) {
        AuthDefaultSource source = AuthSourceMapping.getSourceByKey(key);
        AuthConfig config = properties.getSocial().getType().get(source);
        switch (source) {
            case GITHUB:
                return new AuthGithubRequest(config, authStateCache);
            case WEIBO:
                return new AuthWeiboRequest(config, authStateCache);
            case GITEE:
                return new AuthGiteeRequest(config, authStateCache);
            case DINGTALK:
                return new AuthDingTalkRequest(config, authStateCache);
            case BAIDU:
                return new AuthBaiduRequest(config, authStateCache);
            case CSDN:
                return new AuthCsdnRequest(config, authStateCache);
            case CODING:
                return new AuthCodingRequest(config, authStateCache);
            case TENCENT_CLOUD:
                return new AuthTencentCloudRequest(config, authStateCache);
            case OSCHINA:
                return new AuthOschinaRequest(config, authStateCache);
            case ALIPAY:
                return new AuthAlipayRequest(config, authStateCache);
            case QQ:
                return new AuthQqRequest(config, authStateCache);
            case WECHAT_OPEN:
                return new AuthWeChatOpenRequest(config, authStateCache);
            case WECHAT_MP:
                return new AuthWeChatMpRequest(config, authStateCache);
            case TAOBAO:
                return new AuthTaobaoRequest(config, authStateCache);
            case GOOGLE:
                return new AuthGoogleRequest(config, authStateCache);
            case FACEBOOK:
                return new AuthFacebookRequest(config, authStateCache);
            case DOUYIN:
                return new AuthDouyinRequest(config, authStateCache);
            case LINKEDIN:
                return new AuthLinkedinRequest(config, authStateCache);
            case MICROSOFT:
                return new AuthMicrosoftRequest(config, authStateCache);
            case MI:
                return new AuthMiRequest(config, authStateCache);
            case TOUTIAO:
                return new AuthToutiaoRequest(config, authStateCache);
            case TEAMBITION:
                return new AuthTeambitionRequest(config, authStateCache);
            case RENREN:
                return new AuthRenrenRequest(config, authStateCache);
            case PINTEREST:
                return new AuthPinterestRequest(config, authStateCache);
            case STACK_OVERFLOW:
                return new AuthStackOverflowRequest(config, authStateCache);
            case HUAWEI:
                return new AuthHuaweiRequest(config, authStateCache);
            case WECHAT_ENTERPRISE:
                return new AuthWeChatEnterpriseRequest(config, authStateCache);
            default:
                throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }
    }

    private static class AuthSourceMapping{

        private static Map<String,AuthDefaultSource> source = new HashMap<>();

        static {
            source.put("github", AuthDefaultSource.GITHUB);
            source.put("weibo",AuthDefaultSource.WEIBO);
            source.put("gitee",AuthDefaultSource.GITEE);
            source.put("dingtalk",AuthDefaultSource.DINGTALK);
            source.put("baidu",AuthDefaultSource.BAIDU);
            source.put("csdn",AuthDefaultSource.CSDN);
            source.put("coding",AuthDefaultSource.CODING);
            source.put("tencent_cloud",AuthDefaultSource.TENCENT_CLOUD);
            source.put("oschina",AuthDefaultSource.OSCHINA);
            source.put("alipay",AuthDefaultSource.ALIPAY);
            source.put("qq",AuthDefaultSource.QQ);

            source.put("wechat_mp",AuthDefaultSource.WECHAT_MP);
            source.put("wechat_open",AuthDefaultSource.WECHAT_OPEN);
            source.put("taobao",AuthDefaultSource.TAOBAO);
            source.put("google",AuthDefaultSource.GOOGLE);
            source.put("facebook",AuthDefaultSource.FACEBOOK);
            source.put("douyin",AuthDefaultSource.DOUYIN);
            source.put("linkedin",AuthDefaultSource.LINKEDIN);
            source.put("microsoft",AuthDefaultSource.MICROSOFT);
            source.put("mi",AuthDefaultSource.MI);
            source.put("toutiao",AuthDefaultSource.TOUTIAO);
            source.put("teambition",AuthDefaultSource.TEAMBITION);
            source.put("renren",AuthDefaultSource.RENREN);
            source.put("pinterest",AuthDefaultSource.PINTEREST);
            source.put("stack_overflow",AuthDefaultSource.STACK_OVERFLOW);
            source.put("huawei",AuthDefaultSource.HUAWEI);
            source.put("wechat_enterprise",AuthDefaultSource.WECHAT_ENTERPRISE);
        }

        public static AuthDefaultSource getSourceByKey(String key){
            return source.get(key);
        }
    }
}
