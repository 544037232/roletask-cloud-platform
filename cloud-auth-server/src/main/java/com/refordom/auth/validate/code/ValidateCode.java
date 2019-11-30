package com.refordom.auth.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : 王晟权
 * @date : 2019/6/12 14:15
 */
public class ValidateCode implements Serializable {

    /**
     * 校验所需的key
     */
    private String key;

    /**
     * 校验码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
