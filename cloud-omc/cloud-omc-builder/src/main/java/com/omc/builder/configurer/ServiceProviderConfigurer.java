package com.omc.builder.configurer;

import com.omc.builder.ActionBuilder;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.filter.ResultBuilderFilter;
import com.omc.builder.filter.ServiceProviderFilter;
import com.omc.builder.filter.ResultNullProcessing;
import com.omc.builder.filter.ResultProcessing;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<ServiceProviderConfigurer<B>, B> {

    private List<ServiceProvider> serviceProviders = new ArrayList<>();

    private ResultProcessing resultProcessing;

    @Override
    public void init(B builder) throws Exception {
        if (resultProcessing == null){
            resultProcessing = new ResultNullProcessing();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        ServiceProviderFilter filterServiceInterceptor = new ServiceProviderFilter(serviceProviders);

        ResultBuilderFilter filterResultInterceptor = new ResultBuilderFilter(resultProcessing);

        builder.addFilter(filterServiceInterceptor);
        builder.addFilter(filterResultInterceptor);
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
