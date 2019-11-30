package com.refordom.auth.validate;

import com.refordom.common.security.constant.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 *
 * @author : 王晟权
 * @date : 2019/6/11 22:36
 */
@RestController
public class ValidateCodeController {

    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable String type) throws Exception {

        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }
}
