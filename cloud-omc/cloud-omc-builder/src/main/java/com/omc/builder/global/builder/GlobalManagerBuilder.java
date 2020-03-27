package com.omc.builder.global.builder;

import com.omc.builder.global.GlobalManager;
import com.omc.builder.global.feature.FutureManager;
import com.omc.object.AbstractConfiguredObjectBuilder;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class GlobalManagerBuilder extends AbstractConfiguredObjectBuilder<GlobalManager, GlobalManagerBuilder>
        implements ObjectBuilder<GlobalManager> {

    private final List<ObjectBuilder<? extends FutureManager>> futureManagerBuilders = new ArrayList<>();

    public GlobalManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected GlobalManager performBuild() throws Exception {

        GlobalManager globalManager = new GlobalManager();

        List<FutureManager> futureManagers = new ArrayList<>();

        for (ObjectBuilder<? extends FutureManager> futureManagerBuilder : futureManagerBuilders) {
            futureManagers.add(futureManagerBuilder.build());
        }

        globalManager.setFutureManagers(futureManagers);

        return globalManager;
    }

    public GlobalManagerBuilder addFutureBuilder(ActionFeatureBuilder actionFeatureBuilder) {
        this.futureManagerBuilders.add(actionFeatureBuilder);
        return this;
    }
}
