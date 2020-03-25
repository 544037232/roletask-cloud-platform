package com.omc.builder.serve;

import com.omc.builder.ProviderManagerProxy;
import com.omc.builder.api.ProviderManager;
import com.omc.object.AbstractConfiguredObjectBuilder;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectPostProcessor;
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

    private final List<ObjectBuilder<? extends ProviderManager>> actionFilterChainBuilders = new ArrayList<>();

    public ServeRequest(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected Filter performBuild() throws Exception {

        List<ProviderManager> providerManagers = new ArrayList<>(actionFilterChainBuilders.size());

        for (ObjectBuilder<? extends ProviderManager> providerManagerBuilder : actionFilterChainBuilders) {
            providerManagers.add(providerManagerBuilder.build());
        }

        ProviderManagerProxy filterChainProxy = new ProviderManagerProxy(providerManagers);


        return filterChainProxy;
    }


    public ServeRequest addFilterChainBuilder(
            ObjectBuilder<? extends ProviderManager> actionFilterChainBuilder) {
        this.actionFilterChainBuilders.add(actionFilterChainBuilder);
        return this;
    }

}
