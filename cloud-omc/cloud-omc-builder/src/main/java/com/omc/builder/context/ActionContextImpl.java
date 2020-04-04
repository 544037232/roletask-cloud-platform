package com.omc.builder.context;

import com.omc.builder.ResultToken;
import com.omc.builder.validator.ParamAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionContextImpl implements ActionContext {

    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ================================================================================================

    private ParamAdapter paramAdapter;

    private Map<Object,Object> process = new ConcurrentHashMap<>();

    private ResultToken resultToken;

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
    public void setResult(ResultToken result) {
        this.resultToken = result;
    }

    @Override
    public ResultToken getResult() {
        return resultToken;
    }

}
