package com.refordom.app.core;

import com.refordom.app.core.validator.ParamBean;

import java.io.Serializable;
import java.util.List;

/**
 * 应用上下文，获取应用详情，为上下文设定应用详情，在请求执行完成后自动清空
 *
 * @author pricess.wang
 * @date 2019/12/19 15:24
 */
public interface AppContext extends Serializable {

    AppDetails getAppDetails();

    void setAppDetails(AppDetails appDetails);

    ParamBean getParamBean();

    void setParamBean(ParamBean paramBean);

    void setProcessObj(Object key, Object val);

    Object getProcessObj(Object key);

    <T> void addResult(T result);

    <T> List<T> getResult();
}
