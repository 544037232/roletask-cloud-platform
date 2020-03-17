package com.refordom.common.action.builder.global;

import com.refordom.common.builder.ObjectPostProcessor;
import com.refordom.common.builder.ObjectPostProcessorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;

@Import(ObjectPostProcessorConfiguration.class)
public class ActionGlobalConfiguration {

    private ObjectPostProcessor<Object> objectPostProcessor;

    private ActionManager actionManager;

    @Autowired(required = false)
    public void setGlobalActionConfigurers(
            List<GlobalActionConfigurerAdapter> configurers) throws Exception {
        configurers.sort(AnnotationAwareOrderComparator.INSTANCE);

        ActionManagerBuilder actionManagerBuilder = new ActionManagerBuilder(objectPostProcessor);

        for (GlobalActionConfigurerAdapter globalAdapter : configurers) {
            actionManagerBuilder.apply(globalAdapter);
        }

        actionManager = actionManagerBuilder.build();

    }

    @Autowired
    public void setObjectPostProcessor(ObjectPostProcessor<Object> objectPostProcessor) {
        this.objectPostProcessor = objectPostProcessor;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
