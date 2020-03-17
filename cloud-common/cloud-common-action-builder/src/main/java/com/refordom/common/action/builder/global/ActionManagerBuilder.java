package com.refordom.common.action.builder.global;

import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class ActionManagerBuilder extends AbstractConfiguredObjectBuilder<ActionManager, ActionManagerBuilder> implements ActionProviderManagerBuilder<ActionManagerBuilder>{

    private boolean debugEnabled = false;

    private Collection<String> urlPatterns = new ArrayList<>();

    protected ActionManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    @Override
    protected ActionManager performBuild() throws Exception {
        ActionManager actionManager = new ActionManager();

        actionManager.setDebugEnabled(debugEnabled);
        actionManager.setUrlPatterns(urlPatterns);
        return actionManager;
    }

    public ActionManagerBuilder debug(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }

    public ActionManagerBuilder addUrlPattern(String urlPattern) {
        this.urlPatterns.add(urlPattern);
        return this;
    }

    public Collection<String> getUrlPatterns() {
        return urlPatterns;
    }

}
