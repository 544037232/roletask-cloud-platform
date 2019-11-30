package com.refordom.common.security.util;

import com.refordom.common.security.constant.SecurityConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p>应用客户端工具类</p>
 *
 * @author pricess.wang
 * @date 2019/11/29 10:28
 */
public class ClientUtils {

    public static String[] getClientInfo(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(SecurityConstants.BASIC_PREFIX)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = extractAndDecodeHeader(header);
        assert tokens.length == 2;

        return tokens;
    }

    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static String[] extractAndDecodeHeader(String header) {

        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = decoder.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
