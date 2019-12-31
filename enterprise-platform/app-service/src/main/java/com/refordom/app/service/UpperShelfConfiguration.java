package com.refordom.app.service;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.service.constant.ActionConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class UpperShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionRequestMatcher(ActionConstant.UPPER_SHELF)
                .paramsCheck();
    }

    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        System.err.println(antPathMatcher.match("/app/store/*","/app/store/upperShelf"));
    }
}
