package com.omc.builder.filter;

import com.omc.builder.ResultToken;
import com.omc.builder.api.ServiceProvider;
import com.omc.builder.context.ActionContextHolder;
import org.springframework.util.Assert;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务执行类过滤器
 */
public class ServiceProviderFilter implements Filter {

    private final List<ServiceProvider> serviceProviders = new ArrayList<>();

    public ServiceProviderFilter(List<ServiceProvider> serviceProviders) {
        Assert.notNull(serviceProviders, "this service providers can not be null");
        this.serviceProviders.addAll(serviceProviders);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (serviceProviders.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        ResultToken result = ActionContextHolder.getContext().getResult();

        if (result != null){

            for (ServiceProvider serviceProvider : serviceProviders) {

                if (serviceProvider.supports(result.getClass())){
                    result = serviceProvider.provider(result);
                }
            }

            ActionContextHolder.getContext().setResult(result);
        }

        chain.doFilter(request,response);
    }
}
