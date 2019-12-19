package com.refordom.app.config;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:33
 */
public interface AppProvider {

    AppDetails provider(AppDetails appDetails);

    boolean supports(Class<?> context);
}
