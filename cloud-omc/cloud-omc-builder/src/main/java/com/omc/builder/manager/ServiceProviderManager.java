package com.omc.builder.manager;

import com.omc.builder.ResultToken;
import com.omc.builder.api.ServiceManager;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.filter.ResultProcessing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class ServiceProviderManager implements ServiceManager {

    private final LinkedList<ServiceProvider> serviceProviders;

    private ResultProcessing resultProcessing;

    public ServiceProviderManager(LinkedList<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @Override
    public ResultToken attemptExecutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResultToken result = resultProcessing.init(request,response);

        for (ServiceProvider serviceProvider : serviceProviders) {

            if (serviceProvider.supports(serviceProvider.getClass())) {
                serviceProvider.provider(result);
            }
        }

        return result;
    }

    public void setResultProcessing(ResultProcessing resultProcessing) {
        this.resultProcessing = resultProcessing;
    }
}
