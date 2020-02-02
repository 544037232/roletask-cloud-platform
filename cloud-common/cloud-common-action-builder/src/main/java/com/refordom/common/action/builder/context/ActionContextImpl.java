package com.refordom.common.action.builder.context;

import com.refordom.common.action.builder.validator.ParamBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionContextImpl implements ActionContext {

    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ================================================================================================

    private ParamBean paramBean;

    private List<Object> results = new ArrayList<>();

    private Map<Object,Object> process = new ConcurrentHashMap<>();

    // ~ Methods
    // ========================================================================================================

    @Override
    public ParamBean getParamBean() {
        return paramBean;
    }

    @Override
    public void setParamBean(ParamBean paramBean) {
        this.paramBean = paramBean;
    }

    @Override
    public void setProcessObj(Object key, Object val) {
        process.put(key,val);
    }

    @Override
    public Object getProcessObj(Object key) {
        return process.get(key);
    }

    @Override
    public <T> void addResult(T result) {
        results.add(result);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getResult() {
        return (List<T>) results;
    }
}
