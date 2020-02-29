package com.refordom.common.security.validate;

import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 *
 * @author 王晟权
 */
public class InMemoryValidateCodeRepository<C extends ValidateCode> implements ValidateCodeRepository<C> {

    private final Map<String,C> memory = new ConcurrentHashMap<>();

    @Override
    public void save(ServletWebRequest request,C code) {
        memory.put(code.getKey(), code);
    }

    @Override
    public C get(ServletWebRequest request,String key) {
        return memory.get(key);
    }

    @Override
    public void remove(ServletWebRequest request, String key) {
        memory.remove(key);
    }

}
