package com.refordom.app.config;

import com.refordom.app.config.core.AppRequest;
import com.refordom.common.builder.ObjectConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.Assert;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/12 16:43
 */
final class AutowiredAppRequestConfigurersIgnoreParents {

    private final ConfigurableListableBeanFactory beanFactory;

    public AutowiredAppRequestConfigurersIgnoreParents(
            ConfigurableListableBeanFactory beanFactory) {
        Assert.notNull(beanFactory, "beanFactory cannot be null");
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<ObjectConfigurer<Filter, AppRequest>> getAppRequestConfigurers() {
        List<ObjectConfigurer<Filter, AppRequest>> requestAppConfigurers = new ArrayList<>();
        Map<String, AppRequestConfigurer> beansOfType = beanFactory
                .getBeansOfType(AppRequestConfigurer.class);
        for (Map.Entry<String, AppRequestConfigurer> entry : beansOfType.entrySet()) {
            requestAppConfigurers.add(entry.getValue());
        }
        return requestAppConfigurers;
    }
}
