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
        initDefaultParamsCheck();
    }

    private void initDefaultParamsCheck() {
        paramsEmptyCheckFilter.checkRule("app_id","参数app_id不能为空");
        paramsEmptyCheckFilter.checkRule("app_type","参数app_type不能为空");
    }

    @Override
    public void configure(H builder) throws Exception {
        super.configure(builder);
    }

    public ActionParamsCheckConfigurer<H> defaultValue(String paramName, Object value) {
        paramsEmptyCheckFilter.defaultValue(paramName, value);
        return this;
    }

    public ActionParamsCheckConfigurer<H> paramsEmptyCheck(String paramName, String errorMessage) {
        paramsEmptyCheckFilter.checkRule(paramName, errorMessage);
        return this;
    }
}
