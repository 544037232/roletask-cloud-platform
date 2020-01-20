package com.refordom.app.config;

import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.core.AppRequest;
import com.refordom.common.builder.ObjectConfigurer;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * 应用顶级配置入口
 *
 * @author pricess.wang
 * @date 2019/12/13 18:20
 */
@Import({AppConfiguration.class})
public class AppRequestConfiguration {

    private AppRequest appRequest;

    private List<ObjectConfigurer<Filter, AppRequest>> requestAppStoreConfigurers;

    @Bean
    public FilterRegistrationBean<Filter> appFilterChain(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        boolean hasConfigurers = requestAppStoreConfigurers != null
                && !requestAppStoreConfigurers.isEmpty();

        if (!hasConfigurers) {
            AppRequestConfigurerAdapter adapter = objectPostProcessor
                    .postProcess(new AppRequestConfigurerAdapter() {
                    });
            appRequest.apply(adapter);
        }
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(appRequest.build());
        registrationBean.setOrder(Integer.MAX_VALUE);
        registrationBean.setName(AppConstant.APP_FILTER_CHAIN_NAME);
        registrationBean.setUrlPatterns(Collections.singletonList(AppConstant.APP_URL_PATTERNS));
        return registrationBean;
    }

    @Autowired(required = false)
    public void setFilterChainProxyConfigurer(
            ObjectPostProcessor<Object> objectPostProcessor,
            @Value("#{@autowiredAppRequestConfigurersIgnoreParents.getAppRequestConfigurers()}") List<ObjectConfigurer<Filter, AppRequest>> requestConfigurers)
            throws Exception {
        appRequest = objectPostProcessor
                .postProcess(new AppRequest(objectPostProcessor));

        for (ObjectConfigurer<Filter, AppRequest> objectConfigurer : requestConfigurers) {
            appRequest.apply(objectConfigurer);
        }
        this.requestAppStoreConfigurers = requestConfigurers;
    }

    @Bean(name = AppConstant.APP_TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public static AutowiredAppRequestConfigurersIgnoreParents autowiredAppRequestConfigurersIgnoreParents(
            ConfigurableListableBeanFactory beanFactory) {
        return new AutowiredAppRequestConfigurersIgnoreParents(beanFactory);
    }
}
