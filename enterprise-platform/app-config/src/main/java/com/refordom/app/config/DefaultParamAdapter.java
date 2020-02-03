package com.refordom.app.config;

import com.refordom.app.core.AppEnum;
import com.refordom.app.core.constant.ParamConstant;
import com.refordom.common.action.builder.validator.ParamAdapter;
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
public class DefaultParamAdapter implements ParamAdapter {

    @NotBlank
    private String appId;

    @NotNull
    private AppEnum appType;

    public DefaultParamAdapter(HttpServletRequest request) {
        String appId = request.getParameter(ParamConstant.PARAM_APP_ID);

        String appType = request.getParameter(ParamConstant.PARAM_APP_TYPE);

        this.appId = appId;
        this.appType = AppEnum.valuesOfBean(appType);
    }

}
