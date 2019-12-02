package com.refordom.auth.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.refordom.auth.handler.ClientJdbcAuthoritiesTypeHandler;
import com.refordom.auth.handler.ClientJdbcSetTypeHandler;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * @author pricess.wang
 * @date 2019/12/2 14:38
 */
@TableName(value = "sys_oauth_client_details", autoResultMap = true)
public class AuthClientDetails implements ClientDetails {

    @TableId(type = IdType.INPUT, value = "client_id")
    private String clientId;

    @TableField("client_secret")
    private String clientSecret;

    @TableField(typeHandler = ClientJdbcSetTypeHandler.class)
    private Set<String> scope = Collections.emptySet();

    @TableField(typeHandler = ClientJdbcSetTypeHandler.class)
    private Set<String> resourceIds = Collections.emptySet();

    @TableField(value = "authorized_grant_types", typeHandler = ClientJdbcSetTypeHandler.class)
    private Set<String> authorizedGrantTypes = Collections.emptySet();

    @TableField(value = "web_server_redirect_uri", typeHandler = ClientJdbcSetTypeHandler.class)
    private Set<String> registeredRedirectUris;

    @TableField(value = "auto_approve", typeHandler = ClientJdbcSetTypeHandler.class)
    private Set<String> autoApproveScopes;

    @TableField(typeHandler = ClientJdbcAuthoritiesTypeHandler.class)
    private List<GrantedAuthority> authorities = Collections.emptyList();

    @TableField("access_token_validity")
    private Integer accessTokenValiditySeconds;

    @TableField("refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @TableField(value = "additional_information", typeHandler = FastjsonTypeHandler.class)
    private Map<String, Object> additionalInformation = new LinkedHashMap<>();

    @TableField(value = "auth_target")
    private String authTarget;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAutoApproveScopes(Collection<String> autoApproveScopes) {
        this.autoApproveScopes = new HashSet<>(autoApproveScopes);
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    public String getAuthTarget() {
        return authTarget;
    }

    public void setAuthTarget(String authTarget) {
        this.authTarget = authTarget;
    }

    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Collection<String> scope) {
        this.scope = scope == null ? Collections.emptySet()
                : new LinkedHashSet<>(scope);
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Collection<String> resourceIds) {
        this.resourceIds = resourceIds == null ? Collections
                .emptySet() : new LinkedHashSet<>(resourceIds);
    }

    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Collection<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = new LinkedHashSet<>(
                authorizedGrantTypes);
    }

    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris == null ? null
                : new LinkedHashSet<>(registeredRedirectUris);
    }

    private List<String> getAuthoritiesAsStrings() {
        return new ArrayList<>(
                AuthorityUtils.authorityListToSet(authorities));
    }

    private void setAuthoritiesAsStrings(Set<String> values) {
        setAuthorities(AuthorityUtils.createAuthorityList(values
                .toArray(new String[values.size()])));
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        this.authorities = new ArrayList<>(authorities);
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(
            Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAdditionalInformation(Map<String, ?> additionalInformation) {
        this.additionalInformation = new LinkedHashMap<>(
                additionalInformation);
    }

    public Map<String, Object> getAdditionalInformation() {
        return Collections.unmodifiableMap(this.additionalInformation);
    }

    public void addAdditionalInformation(String key, Object value) {
        this.additionalInformation.put(key, value);
    }

}
