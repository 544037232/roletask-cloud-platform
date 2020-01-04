package com.refordom.app.core.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的参数解析器，自定义解析包装对象时需注意参数的类型或长度等，
 * 具体的包装对象必须实现ParamBean接口，在属性中使用注解方式申明校验逻辑
 *
 * @author pricess.wang
 * @date 2019/12/31 19:44
 */
public class DefaultActionParamParser implements ActionParamParser {

    @Override
    public ParamBean build(HttpServletRequest request) {
        return new DefaultParamBean(request);
    }
}
