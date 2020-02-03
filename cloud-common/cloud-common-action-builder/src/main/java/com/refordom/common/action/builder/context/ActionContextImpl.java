package com.refordom.common.action.builder.context;

import com.refordom.common.action.builder.validator.ParamAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionContextImpl implements ActionContext {

    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ================================================================================================

    private ParamAdapter paramAdapter;

    private List<Object> results = new ArrayList<>();

    private Map<Object,Object> process = new ConcurrentHashMap<>();

    // ~ Methods
    // ========================================================================================================

    @Override
    public ParamAdapter getParamAdapter() {
        return paramAdapter;
    }

    @Override
    public void setParamAdapter(ParamAdapter paramAdapter) {
        this.paramAdapter = paramAdapter;
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
