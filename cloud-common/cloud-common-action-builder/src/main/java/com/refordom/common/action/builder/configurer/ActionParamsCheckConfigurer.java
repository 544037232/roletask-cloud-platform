package com.refordom.common.action.builder.configurer;

import com.refordom.common.action.builder.ActionBuilder;
import com.refordom.common.action.builder.core.ParamsCheckFilter;
import com.refordom.common.action.builder.validator.ActionParamParser;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:50
 */
public final class ActionParamsCheckConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<ActionParamsCheckConfigurer<B>, B> {

    private final ParamsCheckFilter paramsCheckFilter = new ParamsCheckFilter();

    private ActionParamParser actionParamParser;

    @Override
    public final void init(B builder) throws Exception {
        builder.addFilter(paramsCheckFilter,1);
    }

    @Override
    public void configure(B builder) throws Exception {
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
    public ActionParamsCheckConfigurer<B> actionParamParser(ActionParamParser actionParamParser) {
        this.actionParamParser = actionParamParser;
        return this;
    }
}
