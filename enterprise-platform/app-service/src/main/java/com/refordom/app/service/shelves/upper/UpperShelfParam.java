package com.refordom.app.service.shelves.upper;

import com.refordom.app.core.constant.ParamConstant;
import com.refordom.app.core.validator.DefaultParamBean;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2020/1/2 15:44
 */
@Getter
public class UpperShelfParam extends DefaultParamBean {

    @Range(min = 0, max = 10)
    private String price;

    public UpperShelfParam(HttpServletRequest request) {
        super(request);

        String price = request.getParameter(ParamConstant.PARAM_APP_PRICE);

        this.price = price == null ? "0" : price;
    }
}
