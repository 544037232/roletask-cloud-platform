package com.omc.builder.api;

import com.omc.builder.ResultToken;

public interface ServiceProvider {

    void provider(ResultToken instance);

    boolean supports(Class<?> context);
}
