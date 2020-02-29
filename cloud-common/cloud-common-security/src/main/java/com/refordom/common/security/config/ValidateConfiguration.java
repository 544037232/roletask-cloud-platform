package com.refordom.common.security.config;

import com.refordom.common.security.validate.InMemoryValidateCodeRepository;
import com.refordom.common.security.validate.RedisValidateCodeRepository;
import com.refordom.common.security.validate.ValidateCode;
import com.refordom.common.security.validate.ValidateCodeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/26 11:27
 */
@Configuration
public class ValidateConfiguration {

//    @Bean
//    @ConditionalOnProperty(prefix = "auth1.validate", name = "store", havingValue = "redis")
//    public <C extends ValidateCode> ValidateCodeRepository<C> redisValidateCodeRepository(RedisTemplate<Object, Object> redisTemplate) {
//        return new RedisValidateCodeRepository<>(redisTemplate);
//    }

    @Bean
    @ConditionalOnProperty(prefix = "auth.validate", name = "store", havingValue = "memory" , matchIfMissing = true)
    public <C extends ValidateCode> ValidateCodeRepository<C> inMemoryValidateCodeRepository() {
        return new InMemoryValidateCodeRepository<>();
    }
}
