package com.refordom.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author pricess.wang
 * @date 2019/12/7 14:51
 */
@JsonIgnoreProperties(value = {"userId", "password", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"})
public class AuthUserDetail extends User {

    @Getter
    private Long userId;

    @Getter
    private String nickname;

    @Getter
    private String phone;

    @Getter
    private String email;

    @Getter
    private String avatar;

    public AuthUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUserDetail(Long userId, String nickname, String username, String password, String phone, String email, String avatar, boolean enabled,
                          boolean accountNonExpired, boolean credentialsNonExpired,
                          boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
    }
}
