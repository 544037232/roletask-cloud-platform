package com.omc.builder.filter;

import com.omc.builder.ResultToken;
import com.omc.builder.api.StoreProvider;
import com.omc.builder.context.ActionContextHolder;
import org.springframework.util.Assert;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreProviderFilter implements Filter {

    private List<StoreProvider> storeProviders = new ArrayList<>();

    public StoreProviderFilter(List<StoreProvider> storeProviders) {
        Assert.notNull(storeProviders, "this store providers can not be null");
        this.storeProviders.addAll(storeProviders);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (storeProviders.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        ResultToken result = ActionContextHolder.getContext().getResult();

        if (result != null) {

            for (StoreProvider storeProvider : storeProviders) {
                storeProvider.provider(result);
            }

        }

        chain.doFilter(request, response);
    }
}
