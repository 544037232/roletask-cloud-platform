package com.omc.builder.global.feature;

/**
 * 全局插件管理
 */
public class FutureManager {

    private String path;

    private boolean debugEnabled;

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
