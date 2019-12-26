package com.refordom.app.config.core;

import com.refordom.app.config.AppDebugFilter;
import com.refordom.app.config.AppFilterChain;
import com.refordom.app.config.AppFilterChainProxy;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:24
 */
public class AppRequest extends AbstractConfiguredObjectBuilder<Filter, AppRequest> implements
        ObjectBuilder<Filter> {

    private boolean debugEnabled = true;

    private final Log logger = LogFactory.getLog(getClass());

    private final List<ObjectBuilder<? extends AppFilterChain>> appFilterChainBuilders = new ArrayList<>();

    public AppRequest(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected Filter performBuild() throws Exception {
        int chainSize = appFilterChainBuilders.size();

        List<AppFilterChain> securityFilterChains = new ArrayList<>(chainSize);

        for (ObjectBuilder<? extends AppFilterChain> appFilterChainBuilder : appFilterChainBuilders) {
            securityFilterChains.add(appFilterChainBuilder.build());
        }
        AppFilterChainProxy filterChainProxy = new AppFilterChainProxy(securityFilterChains);

        filterChainProxy.afterPropertiesSet();

        Filter result = filterChainProxy;
        if (debugEnabled) {
            logger.warn("\n\n"
                    + "********************************************************************\n"
                    + "**********        App Action debugging is enabled.       *************\n"
                    + "**********    This may include sensitive information.  *************\n"
                    + "**********      Do not use in a production system!     *************\n"
                    + "********************************************************************\n\n");
            result = new AppDebugFilter(filterChainProxy);
        }
        postBuildAction.run();
        return result;
    }

    public AppRequest addAppFilterChainBuilder(
            ObjectBuilder<? extends AppFilterChain> appFilterChainBuilder) {
        this.appFilterChainBuilders.add(appFilterChainBuilder);
        return this;
    }

    public AppRequest debug(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }
}
