package com.refordom.common.security.oauth;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.refordom.common.core.util.R;
import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.properties.AuthRequestFactory;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>oauth第三方协议登录 <p/>
 *
 * @author pricess.wang
 * @date 2019/8/12 19:19
 */
@RestController
@RequestMapping(SecurityConstants.OAUTH)
public class OauthController {

    @Resource
    private AuthRequestFactory authRequestFactory;

    @RequestMapping("/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = authRequestFactory.get(source);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) {
        AuthRequest authRequest = authRequestFactory.get(source);
        return authRequest.login(callback);
    }

    @Resource
    private TokenStore tokenStore;

    /**
     * 退出token
     */
    @PostMapping("/logout")
    public R logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StrUtil.isBlank(authHeader)) {
            return R.failed(HttpStatus.HTTP_ACCEPTED,"退出失败，token 为空");
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
            return R.failed(HttpStatus.HTTP_ACCEPTED,"token 无效,请刷新页面重试.");
        }

        // 清空access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        return R.ok(Boolean.TRUE);
    }

}
