package com.refordom.auth.service;

import com.refordom.auth.constant.ClientDetailConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;

import javax.annotation.Resource;

/**
 * @author pricess.wang
 * @date 2019/12/2 14:37
 */
public class UserClientDetailsService implements ClientDetailsService {

    @Resource
    private AuthClientDetailsService authClientDetailsService;

    @Override
    @Cacheable(value = ClientDetailConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return authClientDetailsService.getById(clientId);
    }

}
