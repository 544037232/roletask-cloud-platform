package com.refordom.app.config;

import com.refordom.app.core.AppEnum;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.common.action.builder.validator.ParamBean;
import lombok.Getter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author pricess.wang
 * @date 2019/12/31 19:40
 */
@ToString
@Getter
public class DefaultParamBean implements ParamBean {

    @NotBlank
    private String appId;

    @NotNull
    private AppEnum appType;

    public DefaultParamBean(HttpServletRequest request) {
        String appId = request.getParameter(ParamConstant.PARAM_APP_ID);

        String appType = request.getParameter(ParamConstant.PARAM_APP_TYPE);

        this.appId = appId;
        this.appType = AppEnum.valuesOfBean(appType);
    }

}
