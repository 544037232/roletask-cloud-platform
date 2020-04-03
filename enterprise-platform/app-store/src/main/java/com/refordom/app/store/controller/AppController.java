package com.refordom.app.store.controller;

import com.refordom.common.security.util.SecurityUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/test")
    @PostAuthorize("hasAnyRole('ROLE_MemberS')")
    public String test() {
        return "hello " + ((KeycloakPrincipal<RefreshableKeycloakSecurityContext>)SecurityUtils.getAuthentication().getPrincipal()).getKeycloakSecurityContext().getToken().getGivenName();
    }

}
