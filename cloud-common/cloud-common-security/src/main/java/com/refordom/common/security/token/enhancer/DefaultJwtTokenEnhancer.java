package com.refordom.common.security.token.enhancer;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>默认的jwt token增强器，可覆盖当前方法，在不同应用中定制不同的token信息</p>
 *
 * @author pricess.wang
 * @date 2019/9/23 15:31
 */
public class DefaultJwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>(1);
        info.put("company", "销售订单");

        Map<String, Object> account = new HashMap<>(2);

        account.put("accountKey", 1406);
        account.put("matchDirection", 1);
        info.put("account", account);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
