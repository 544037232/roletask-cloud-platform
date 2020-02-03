package com.refordom.common.action.builder.context;

import com.refordom.common.action.builder.validator.ParamAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 应用上下文，获取应用详情，为上下文设定应用详情，在请求执行完成后自动清空
 *
 * @author pricess.wang
 * @date 2019/12/19 15:24
 */
public interface ActionContext extends Serializable {

    ParamAdapter getParamAdapter();

    void setParamAdapter(ParamAdapter paramBean);

    void setProcessObj(Object key, Object val);

    Object getProcessObj(Object key);

    <T> void addResult(T result);

    <T> List<T> getResult();
}
