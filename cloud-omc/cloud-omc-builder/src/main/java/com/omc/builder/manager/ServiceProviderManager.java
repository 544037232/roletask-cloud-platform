package com.omc.builder.manager;

import com.omc.builder.ResultToken;
import com.omc.builder.api.ServiceManager;
import com.omc.builder.api.ServiceProvider;

import java.util.LinkedList;

public class ServiceProviderManager implements ServiceManager {

    private final LinkedList<ServiceProvider> serviceProviders;

    private Class<? extends ResultToken> result;

    public ServiceProviderManager(LinkedList<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @Override
    public ResultToken attemptExecutor() {
        ResultToken instance;

        try {
            instance = result.newInstance();
        } catch (Exception e) {
            instance = new ResultToken() {
            };
        }
        for (ServiceProvider serviceProvider : serviceProviders) {

            if (serviceProvider.supports(serviceProvider.getClass())) {
                serviceProvider.provider(instance);
            }
        }

        return instance;
    }

    public void setResultType(Class<? extends ResultToken> result) {
        this.result = result;
    }
}
