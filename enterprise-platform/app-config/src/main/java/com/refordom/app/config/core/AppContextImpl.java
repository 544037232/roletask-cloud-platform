package com.refordom.app.config.core;

import com.refordom.app.config.AppDetails;
import com.refordom.app.model.AppModel;

import java.util.Objects;

public class AppContextImpl implements AppContext {

    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ================================================================================================

    private AppModel appModel;

    private AppDetails appDetails;

    // ~ Methods
    // ========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppContextImpl) {
            AppContextImpl test = (AppContextImpl) obj;

            if ((this.getAppModel() == null) && (test.getAppModel() == null)
                    && (this.getAppDetails() == null) && (test.getAppDetails() == null)) {
                return true;
            }

            return (this.getAppModel() != null) && (test.getAppModel() != null)
                    && this.getAppModel().equals(test.getAppModel())
                    && (this.getAppDetails() != null) && (test.getAppDetails() != null)
                    && this.getAppDetails().equals(test.getAppDetails());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appModel, appDetails);
    }

    @Override
    public String toString() {
        return "AppContextImpl{" +
                "appModel=" + appModel +
                ", appDetails=" + appDetails +
                '}';
    }

    @Override
    public AppModel getAppModel() {
        return appModel;
    }

    @Override
    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    @Override
    public AppDetails getAppDetails() {
        return appDetails;
    }

    @Override
    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }
}
