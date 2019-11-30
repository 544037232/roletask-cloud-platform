package com.refordom.auth.validate.repository;

import com.refordom.auth.validate.ValidateCodeRepository;
import com.refordom.auth.validate.code.ValidateCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 *
 * @author 王晟权
 */
@Component
public class RedisValidateCodeRepository<C extends ValidateCode> implements ValidateCodeRepository<C> {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request,C code) {
        redisTemplate.opsForValue().set(code.getKey(), code, 3, TimeUnit.MINUTES);
    }

    @Override
    public C get(ServletWebRequest request,String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return (C) value;
    }

    @Override
    public void remove(ServletWebRequest request, String key) {
        redisTemplate.delete(key);
    }

}
