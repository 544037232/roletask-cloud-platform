package com.refordom.common.action.builder.core;

import com.refordom.common.action.builder.ActionDebugFilter;
import com.refordom.common.action.builder.ActionFilterChain;
import com.refordom.common.action.builder.ActionFilterChainProxy;
import com.refordom.common.action.builder.global.ActionManager;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:24
 */
@Slf4j
public class ServeRequest extends AbstractConfiguredObjectBuilder<Filter, ServeRequest> implements
        ObjectBuilder<Filter> {

    private final List<ObjectBuilder<? extends ActionFilterChain>> actionFilterChainBuilders = new ArrayList<>();

    private ActionManager actionManager;

    public ServeRequest(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected Filter performBuild() throws Exception {
        int chainSize = actionFilterChainBuilders.size();

        List<ActionFilterChain> actionFilterChains = new ArrayList<>(chainSize);

        for (ObjectBuilder<? extends ActionFilterChain> actionFilterChainBuilder : actionFilterChainBuilders) {
            actionFilterChains.add(actionFilterChainBuilder.build());
        }
        ActionFilterChainProxy filterChainProxy = new ActionFilterChainProxy(actionFilterChains);

        filterChainProxy.afterPropertiesSet();

        Filter result = filterChainProxy;
        if (actionManager.isDebugEnabled()) {
            log.warn("\n\n"
                    + "********************************************************************\n"
                    + "**********        App Action debugging is enabled.     *************\n"
                    + "**********    This may include sensitive information.  *************\n"
                    + "**********      Do not use in a production system!     *************\n"
                    + "********************************************************************\n\n");
            result = new ActionDebugFilter(filterChainProxy);
        }
        postBuildAction.run();
        return result;
    }

    public ServeRequest addFilterChainBuilder(
            ObjectBuilder<? extends ActionFilterChain> actionFilterChainBuilder) {
        this.actionFilterChainBuilders.add(actionFilterChainBuilder);
        return this;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
