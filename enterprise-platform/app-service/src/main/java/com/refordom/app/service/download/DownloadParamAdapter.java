package com.refordom.app.service.download;

import com.refordom.app.config.DefaultParamAdapter;
import com.refordom.app.core.constant.ParamConstant;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @author pricess.wang
 * @date 2020/1/16 18:00
 */
@Getter
public class DownloadParamAdapter extends DefaultParamAdapter {

    @NotBlank
    private String version;

    public DownloadParamAdapter(HttpServletRequest request) {
        super(request);
        this.version = request.getParameter(ParamConstant.PARAM_VERSION);
    }
}
