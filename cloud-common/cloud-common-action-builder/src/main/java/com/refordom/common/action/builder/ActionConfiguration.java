package com.refordom.common.action.builder;

import com.refordom.common.action.builder.constant.ActionConstant;
import com.refordom.common.action.builder.core.ServeRequest;
import com.refordom.common.action.builder.global.ActionGlobalConfiguration;
import com.refordom.common.action.builder.global.ActionManager;
import com.refordom.common.builder.ObjectConfigurer;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.List;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 18:38
 */
@Configuration
@Import(ActionGlobalConfiguration.class)
public class ActionConfiguration {

    private ServeRequest serveRequest;

    private List<ObjectConfigurer<Filter, ServeRequest>> requestConfigurers;

    @Bean(ActionConstant.ACTION_FILTER_CHAIN_NAME)
    public FilterRegistrationBean<Filter> actionFilterChain(ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        boolean hasConfigurers = requestConfigurers != null
                && !requestConfigurers.isEmpty();

        if (!hasConfigurers) {
            ActionRequestConfigurerAdapter adapter = objectPostProcessor
                    .postProcess(new ActionRequestConfigurerAdapter() {
                    });
            serveRequest.apply(adapter);
        }
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(serveRequest.build());
        registrationBean.setOrder(Integer.MAX_VALUE);
        registrationBean.setName(ActionConstant.ACTION_FILTER_CHAIN_NAME);
        registrationBean.setUrlPatterns(serveRequest.getActionManager().getUrlPatterns());
        return registrationBean;
    }

    @Autowired(required = false)
    public void setFilterChainProxyConfigurer(
            ObjectPostProcessor<Object> objectPostProcessor,
            @Value("#{@autowiredRequestConfigurersIgnoreParents.getRequestConfigurers()}") List<ObjectConfigurer<Filter, ServeRequest>> requestConfigurers)
            throws Exception {
        serveRequest = objectPostProcessor
                .postProcess(new ServeRequest(objectPostProcessor));

        for (ObjectConfigurer<Filter, ServeRequest> objectConfigurer : requestConfigurers) {
            serveRequest.apply(objectConfigurer);
        }
        this.requestConfigurers = requestConfigurers;
    }

    @Bean(name = ActionConstant.ACTION_TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AutowiredActionRequestConfigurersIgnoreParents autowiredRequestConfigurersIgnoreParents(
            ConfigurableListableBeanFactory beanFactory) {
        return new AutowiredActionRequestConfigurersIgnoreParents(beanFactory);
    }
}
