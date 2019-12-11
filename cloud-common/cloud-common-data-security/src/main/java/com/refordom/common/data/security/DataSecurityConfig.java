package com.refordom.common.data.security;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author pricess.wang
 * @date 2019/12/11 15:29
 */
public class DataSecurityConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BeanAttrMetaObjectHandler();
    }
}
