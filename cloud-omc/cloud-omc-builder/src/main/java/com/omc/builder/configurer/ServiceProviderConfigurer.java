package com.omc.builder.configurer;

import com.omc.builder.ActionBuilder;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.filter.ResultNullProcessing;
import com.omc.builder.filter.ResultProcessing;
import com.omc.builder.manager.ServiceProviderManager;

import java.util.LinkedList;

public class ServiceProviderConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<ServiceProviderConfigurer<B>, B> {

    private LinkedList<ServiceProvider> serviceProviders = new LinkedList<>();

    private ResultProcessing resultProcessing;

    @Override
    public void init(B builder) throws Exception {
        if (resultProcessing == null){
            resultProcessing = new ResultNullProcessing();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        ServiceProviderManager serviceProviderManager = new ServiceProviderManager(serviceProviders);

        serviceProviderManager.setResultProcessing(resultProcessing);

        builder.serviceManager(serviceProviderManager);
    }

    public ServiceProviderConfigurer<B> addServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProviders.add(serviceProvider);
        return this;
    }

    public ServiceProviderConfigurer<B> resultProcessing(ResultProcessing resultProcessing) {
        this.resultProcessing = resultProcessing;
        return this;
    }
}
