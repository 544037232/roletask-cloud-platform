package com.refordom.app.config.configurer;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.filter.ParamsEmptyCheckFilter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:50
 */
public class ActionParamsCheckConfigurer<H extends AppActionBuilder<H>>
        extends AbstractAppActionConfigurer<ActionParamsCheckConfigurer<H>, H> {

    private ParamsEmptyCheckFilter paramsEmptyCheckFilter;

    @Override
    public void init(H builder) throws Exception {
        if (paramsEmptyCheckFilter == null) {
            paramsEmptyCheckFilter = new ParamsEmptyCheckFilter();
        }
    }

    @Override
    public void configure(H builder) throws Exception {
        super.configure(builder);
    }

    public ActionParamsCheckConfigurer<H> paramsEmptyCheck(String paramName, String errorMessage) {
        paramsEmptyCheckFilter.setParamRule(paramName, errorMessage);
        return this;
    }
}
