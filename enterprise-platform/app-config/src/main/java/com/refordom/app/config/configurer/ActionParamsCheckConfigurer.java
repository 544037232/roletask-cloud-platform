package com.refordom.app.config.configurer;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.config.filter.ParamsCheckFilter;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.core.validator.ActionParamParser;
import com.refordom.app.core.validator.DefaultActionParamParser;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:50
 */
public final class ActionParamsCheckConfigurer<H extends AppActionBuilder<H>>
        extends AbstractAppActionConfigurer<ActionParamsCheckConfigurer<H>, H> {

    private final ParamsCheckFilter paramsCheckFilter = new ParamsCheckFilter();

    private ActionParamParser actionParamParser = new DefaultActionParamParser();

    @Override
    public final void init(H builder) throws Exception {
        builder.addFilter(paramsCheckFilter)
                .addFilter(new AppDetailsFilter(builder.getSharedObject(AppManager.class)));
    }

    @Override
    public void configure(H builder) throws Exception {
        paramsCheckFilter.setActionParamParser(actionParamParser);
    }

    public ActionParamParser getActionParamParser() {
        return actionParamParser;
    }

    /**
     * 可自定义参数校验解析器，用于对不同的包装参数对象进行统一校验
     *
     * @param actionParamParser 解析器
     */
    public void actionParamParser(ActionParamParser actionParamParser) {
        this.actionParamParser = actionParamParser;
    }
}
