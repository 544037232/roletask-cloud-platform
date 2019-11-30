package com.refordom.common.data;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.sql.SQLUtils;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * <p>持久层配置</p>
 * @author : 王晟权
 * @date : 2019/5/13 15:04
 */
@Configuration
public class MybatisPlusConfig {

    /**
     *	 mybatis-plus分页插件
     */
    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    /**
     * 乐观锁插件，对象中对应的版本字段增加@Version注解，如使用updateById时，自动对版本号+1，无需手动+1
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public MybatisConfiguration mybatisConfiguration(){
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        return mybatisConfiguration;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.filter.slf4j")
    @Primary
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public Slf4jLogFilter slf4jLogFilter() {
        Slf4jLogFilter slf4jLogFilter = new ExecuteSqlFilter();
        slf4jLogFilter.setConnectionLogEnabled(false);
        slf4jLogFilter.setResultSetLogEnabled(false);
        slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
        slf4jLogFilter.setStatementSqlFormatOption(new SQLUtils.FormatOption(true, true));
        return slf4jLogFilter;
    }

}
