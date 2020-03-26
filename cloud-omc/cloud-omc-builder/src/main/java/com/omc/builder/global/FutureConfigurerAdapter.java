package com.omc.builder.global;

import com.omc.builder.global.builder.ActionFutureBuilder;
import com.omc.builder.global.builder.GlobalManagerBuilder;
import com.omc.object.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class FutureConfigurerAdapter implements FutureConfigurer<GlobalManagerBuilder> {

    private ActionFutureBuilder futureBuilder;

    private ApplicationContext context;

    private ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<Object>() {
        public <T> T postProcess(T object) {
            throw new IllegalStateException(
                    ObjectPostProcessor.class.getName()
                            + " is a required bean. Ensure you have used @EnableAppRequest and @Configuration");
        }
    };

    @Override
    public void init(GlobalManagerBuilder builder) throws Exception {

        ActionFutureBuilder actionFutureBuilder = getFutureBuilder();

        builder.addFutureBuilder(actionFutureBuilder);
    }

    private ActionFutureBuilder getFutureBuilder() throws Exception {
        if (futureBuilder != null) {
            return futureBuilder;
        }

        String path = this.getClass().getResource("").getPath();

        futureBuilder = new ActionFutureBuilder(objectPostProcessor, context, path);

        futureBuilder.concurrentLock();

        configure(futureBuilder);

        return futureBuilder;
    }

    protected void configure(ActionFutureBuilder futureBuilder) throws Exception {

    }

    @Override
    public void configure(GlobalManagerBuilder builder) throws Exception {

    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
