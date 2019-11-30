package com.refordom.dev.user.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.refordom.common.core.constant.CommonConstants;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/29 14:42
 */
@JsonIgnoreProperties(value = {"password", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class DevUserDetails extends User {

    @Getter
    private Long id;

    @Getter
    private String mobile;

    public DevUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public DevUserDetails(DevUserInfo devUserInfo) {
        super(devUserInfo.getNickname(),
                devUserInfo.getPassword(),
                !devUserInfo.getStatus().equals(CommonConstants.STATUS_DEL),
                true,
                true,
                !devUserInfo.getStatus().equals(CommonConstants.STATUS_LOCK),
                AuthorityUtils.createAuthorityList("read,write"));

        this.id = devUserInfo.getId();
        this.mobile = devUserInfo.getMobile();
    }
}
