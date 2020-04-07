package com.omc.builder.component.registrar;

import com.omc.builder.component.lock.ZkLockComponent;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class ZooKeeperLockRegistrar implements ImportBeanDefinitionRegistrar {

    private ApplicationContext context;

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        BeanDefinition envBeanDefinition = registry.getBeanDefinition(Environment.class.getName());
//
//        Environment env = (Environment) envBeanDefinition;

        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .rootBeanDefinition(ZkLockComponent.class);

        builder.addPropertyValue("connectionString","192.168.3.72:2181,192.168.3.72:2182,192.168.3.72:2183");
        builder.addPropertyValue("prefix","lock&zk");

        registry.registerBeanDefinition("lock$ZkLockComponent",
                builder.getBeanDefinition());
    }

}
