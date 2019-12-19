package com.refordom.app.config.configurer;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.filter.AppModelContextFilter;
import com.refordom.app.config.filter.ParamsEmptyCheckFilter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:50
 */
public class ActionParamsCheckConfigurer<H extends AppActionBuilder<H>>
        extends AbstractAppActionConfigurer<ActionParamsCheckConfigurer<H>, H> {

    private final ParamsEmptyCheckFilter paramsEmptyCheckFilter = new ParamsEmptyCheckFilter();

    @Override
    public final void init(H builder) throws Exception {
        builder.addFilter(paramsEmptyCheckFilter)
                .addFilter(new AppModelContextFilter());
        initDefaultParamsCheck();
    }

    private void initDefaultParamsCheck() {
        paramsEmptyCheckFilter.checkRule("app_id", "参数app_id不能为空");
        paramsEmptyCheckFilter.checkRule("app_type", "参数app_type不能为空");
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
