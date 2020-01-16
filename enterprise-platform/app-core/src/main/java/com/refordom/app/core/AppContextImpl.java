package com.refordom.app.core;

import com.refordom.app.core.validator.ParamBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppContextImpl implements AppContext {

    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ================================================================================================

    private AppDetails appDetails;

    private ParamBean paramBean;

    private List<Object> results = new ArrayList<>();

    // ~ Methods
    // ========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppContextImpl) {
            AppContextImpl test = (AppContextImpl) obj;

            if ((this.getAppDetails() == null) && (test.getAppDetails() == null)) {
                return true;
            }

            return ((this.getAppDetails() != null) && (test.getAppDetails() != null)
                    && this.getAppDetails().equals(test.getAppDetails()));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appDetails);
    }

    @Override
    public String toString() {
        return "AppContextImpl{" +
                ", appDetails=" + appDetails +
                '}';
    }

    @Override
    public AppDetails getAppDetails() {
        return appDetails;
    }

    @Override
    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    @Override
    public ParamBean getParamBean() {
        return paramBean;
    }

    @Override
    public void setParamBean(ParamBean paramBean) {
        this.paramBean = paramBean;
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
