package com.refordom.app.config;

import com.refordom.app.model.AppDetails;

/**
 * @author pricess.wang
 * @date 2019/12/17 16:33
 */
public interface AppProvider {

    AppDetails provider(AppToken appDetails);

    boolean supports(Class<?> context);
}
