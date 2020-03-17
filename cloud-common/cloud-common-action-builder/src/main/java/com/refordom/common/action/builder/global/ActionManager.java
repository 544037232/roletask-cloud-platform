package com.refordom.common.action.builder.global;

import java.util.ArrayList;
import java.util.Collection;

public class ActionManager {

    private boolean debugEnabled = false;

    private Collection<String> urlPatterns = new ArrayList<>();

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    public Collection<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(Collection<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
