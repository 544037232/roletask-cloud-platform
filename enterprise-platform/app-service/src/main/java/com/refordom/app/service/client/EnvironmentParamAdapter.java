package com.refordom.app.service.client;

import com.refordom.app.core.constant.ParamConstant;
import com.refordom.common.action.builder.validator.ParamAdapter;
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
public class EnvironmentParamAdapter implements ParamAdapter {

    @NotBlank
    private String clientName;

    @NotBlank
    private String domain;

    public EnvironmentParamAdapter(HttpServletRequest request) {
        this.clientName = request.getParameter(ParamConstant.PARAM_CLIENT_NAME);
        this.domain = request.getParameter(ParamConstant.PARAM_DOMAIN);
    }

    @Getter
    public static class EnvironmentUpdateParamAdapter extends EnvironmentParamAdapter{

        @NotBlank
        private String clientId;

        public EnvironmentUpdateParamAdapter(HttpServletRequest request) {
            super(request);
            this.clientId = request.getParameter(ParamConstant.PARAM_CLIENT_ID);
        }
    }

    @Getter
    public static class EnvironmentDeleteParamAdapter implements ParamAdapter {

        @NotBlank
        private String clientId;

        public EnvironmentDeleteParamAdapter(HttpServletRequest request) {
            this.clientId = request.getParameter(ParamConstant.PARAM_CLIENT_ID);
        }
    }
}
