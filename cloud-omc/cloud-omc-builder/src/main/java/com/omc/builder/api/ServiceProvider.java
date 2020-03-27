package com.omc.builder.api;

import com.omc.builder.ResultToken;

public interface ServiceProvider {

    ResultToken provider(ResultToken instance);

    boolean supports(Class<?> context);
}
