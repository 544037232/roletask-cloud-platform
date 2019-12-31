package com.refordom.app.core.validator;

import com.refordom.app.core.AppEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author pricess.wang
 * @date 2019/12/31 19:40
 */
@ToString
@Getter
@Setter
public class DefaultParamBean implements ParamBean {

    @NotBlank
    private String appId;

    @NotNull
    private AppEnum appType;

}
