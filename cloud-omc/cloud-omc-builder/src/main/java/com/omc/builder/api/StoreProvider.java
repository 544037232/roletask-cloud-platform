package com.omc.builder.api;

import com.omc.builder.ResultToken;

public interface StoreProvider {

    void provider(ResultToken result);

    boolean supports(Class<?> context);
}
