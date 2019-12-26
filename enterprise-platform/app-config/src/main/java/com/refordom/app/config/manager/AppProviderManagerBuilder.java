package com.refordom.app.config.manager;

import com.refordom.app.config.AppProvider;
import com.refordom.common.builder.ObjectBuilder;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:17
 */
public interface AppProviderManagerBuilder<P extends AppProviderManagerBuilder<P>> extends ObjectBuilder<AppManager> {

    P appProvider(AppProvider appProvider);
}
