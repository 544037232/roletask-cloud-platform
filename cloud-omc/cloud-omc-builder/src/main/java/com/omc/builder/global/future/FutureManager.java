package com.omc.builder.global.future;

import com.omc.builder.api.TransactionManager;

/**
 * 全局插件管理
 */
public class FutureManager {

    private TransactionManager transactionManager;

    private String path;

    private boolean debugEnabled;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

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
