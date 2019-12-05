package com.refordom.auth.controller;

import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.util.SecurityUtils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>登录端点</p>
 *
 * @author pricess.wang
 * @date 2019/10/18 15:38
 */
@RestController
@RequestMapping("/token")
public class LoginTokenEndpoint {

    @Resource
    private ClientDetailsService clientDetailsService;
    /**
     * 认证页面
     */
    @GetMapping("/login")
    public ModelAndView require(ModelAndView modelAndView) {
        modelAndView.addObject("tokenForm", SecurityConstants.DEFAULT_AUTH_LOGIN_FORM_ACCESS_TOKEN);
        modelAndView.setViewName("views/login");
        return modelAndView;
    }

    /**
     * 确认授权页面
     */
    @GetMapping("/confirm_access")
    public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
        modelAndView.addObject("scopeList", scopeList.keySet());

        Object auth = session.getAttribute("authorizationRequest");
        if (auth != null) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
            modelAndView.addObject("app", clientDetails.getAdditionalInformation());
            modelAndView.addObject("user", SecurityUtils.getUser());
        }

        modelAndView.setViewName("views/confirm");
        return modelAndView;
    }
}
