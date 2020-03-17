package com.refordom.common.action.builder.global;

import com.refordom.common.builder.ObjectConfigurer;

/**
 * 应用请求适配器，可可配置出各种业务接口，底层已经封装了所有处理逻辑，包括参数校验，业务处理，数据存储（包括事务），消息处理等
 *
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public abstract class GlobalActionConfigurerAdapter implements ObjectConfigurer<ActionManager, ActionManagerBuilder> {

    @Override
    public void init(ActionManagerBuilder builder) throws Exception {
    }

    @Override
    public void configure(ActionManagerBuilder builder) throws Exception {

    }
}
