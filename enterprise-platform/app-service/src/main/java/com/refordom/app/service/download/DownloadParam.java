package com.refordom.app.service.download;

import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.core.validator.DefaultParamBean;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @author pricess.wang
 * @date 2020/1/16 18:00
 */
@Getter
public class DownloadParam extends DefaultParamBean {

    @NotBlank
    private String version;

    public DownloadParam(HttpServletRequest request) {
        super(request);
        this.version = request.getParameter(ParamConstant.PARAM_VERSION);
    }
}
