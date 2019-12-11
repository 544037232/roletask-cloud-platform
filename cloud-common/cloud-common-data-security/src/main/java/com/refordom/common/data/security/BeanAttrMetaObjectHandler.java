package com.refordom.common.data.security;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.refordom.common.security.model.AuthUserDetail;
import com.refordom.common.security.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author pricess.wang
 * @date 2019/12/11 15:28
 */
public class BeanAttrMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("creator", SecurityUtils.<AuthUserDetail>getUser().getNickname(), metaObject);
        this.setFieldValByName("crtTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifier", SecurityUtils.<AuthUserDetail>getUser().getNickname(), metaObject);
        this.setFieldValByName("modTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifier", SecurityUtils.<AuthUserDetail>getUser().getNickname(), metaObject);
        this.setFieldValByName("modTime", LocalDateTime.now(), metaObject);
    }
}
