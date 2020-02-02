package com.refordom.app.service.client;

import com.refordom.app.core.constant.ParamConstant;
import com.refordom.common.action.builder.validator.ParamBean;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:47
 */
@Getter
public class EnvironmentParam implements ParamBean {

    @NotBlank
    private String clientName;

    @NotBlank
    private String domain;

    public EnvironmentParam(HttpServletRequest request) {
        this.clientName = request.getParameter(ParamConstant.PARAM_CLIENT_NAME);
        this.domain = request.getParameter(ParamConstant.PARAM_DOMAIN);
    }

}
