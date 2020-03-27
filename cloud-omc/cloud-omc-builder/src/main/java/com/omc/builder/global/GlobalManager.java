package com.omc.builder.global;

import com.omc.builder.global.feature.FutureManager;

import java.util.List;

public class GlobalManager {

    private List<FutureManager> futureManagers;

    public List<FutureManager> getFutureManagers() {
        return futureManagers;
    }

    public void setFutureManagers(List<FutureManager> futureManagers) {
        this.futureManagers = futureManagers;
    }
}
