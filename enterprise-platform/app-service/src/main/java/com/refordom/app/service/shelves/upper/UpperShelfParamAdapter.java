package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.DefaultParamAdapter;
import com.refordom.app.core.constant.ParamConstant;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2020/1/2 15:44
 */
@Getter
public class UpperShelfParamAdapter extends DefaultParamAdapter {

    @Range(min = 0, max = 10)
    private String price;

    public UpperShelfParamAdapter(HttpServletRequest request) {
        super(request);

        String price = request.getParameter(ParamConstant.PARAM_APP_PRICE);

        this.price = price == null ? "0" : price;
    }
}
