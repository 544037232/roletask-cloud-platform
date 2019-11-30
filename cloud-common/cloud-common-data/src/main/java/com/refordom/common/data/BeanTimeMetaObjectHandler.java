package com.refordom.common.data;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>实体数据对象公用字段赋值</p>
 *
 * @author pricess.wang
 * @date 2019/9/18 10:32
 */
@Component
public class BeanTimeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("crtTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modTime", LocalDateTime.now(), metaObject);
    }
}
