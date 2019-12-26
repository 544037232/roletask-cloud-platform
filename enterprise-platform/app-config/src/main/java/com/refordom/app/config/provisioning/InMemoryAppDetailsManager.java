package com.refordom.app.config.provisioning;

import com.refordom.app.model.AppDetails;
import com.refordom.app.model.AppDetailsManager;
import com.refordom.app.model.entity.App;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryAppDetailsManager implements AppDetailsManager {

    private final Map<String, App> apps = new HashMap<>();

    public InMemoryAppDetailsManager() {
    }

    public InMemoryAppDetailsManager(Collection<AppDetails> apps) {
        for (AppDetails app : apps) {
            createApp(app);
        }
    }

    public InMemoryAppDetailsManager(AppDetails... apps) {
        for (AppDetails app : apps) {
            createApp(app);
        }
    }

    @Override
    public AppDetails getApp(String appId) {
        return apps.get(appId);
    }

    @Override
    public void createApp(AppDetails appDetails) {
        apps.put(appDetails.getAppId(), (App) appDetails);
    }

}
