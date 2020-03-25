package com.omc.builder.configurer;

import com.omc.builder.ActionBuilder;
import com.omc.builder.ResultToken;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.manager.ServiceProviderManager;

import java.util.LinkedList;

public class ServiceProviderConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<ServiceProviderConfigurer<B>, B> {

    private LinkedList<ServiceProvider> serviceProviders = new LinkedList<>();

    private Class<? extends ResultToken> result = ResultToken.class;

    @Override
    public void init(B builder) throws Exception {
        if (serviceProviders.size() == 0) {
            return;
        }
        if (result == null) {
            throw new IllegalArgumentException("the result token class can not be null");
        }

    }

    @Override
    public void configure(B builder) throws Exception {
        ServiceProviderManager serviceProviderManager = new ServiceProviderManager(serviceProviders);

        serviceProviderManager.setResultType(result);

        builder.serviceManager(serviceProviderManager);
    }

    public ServiceProviderConfigurer<B> addServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProviders.add(serviceProvider);
        return this;
    }

    public ServiceProviderConfigurer<B> resultTokenType(Class<? extends ResultToken> result) {
        this.result = result;
        return this;
    }
}
