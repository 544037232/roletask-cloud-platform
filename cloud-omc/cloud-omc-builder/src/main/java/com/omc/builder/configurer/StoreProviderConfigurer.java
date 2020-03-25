package com.omc.builder.configurer;

import com.omc.builder.ActionBuilder;
import com.omc.builder.api.StoreProvider;
import com.omc.builder.api.TransactionManager;
import com.omc.builder.global.future.FutureManager;
import com.omc.builder.global.transaction.DefaultNoneTransactionManager;
import com.omc.builder.manager.StoreProviderManager;

import java.util.LinkedList;

public class StoreProviderConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<StoreProviderConfigurer<B>, B> {

    private LinkedList<StoreProvider> storeProviders = new LinkedList<>();

    private TransactionManager transactionManager;

    private final FutureManager futureManager;

    public StoreProviderConfigurer(FutureManager futureManager) {
        this.futureManager = futureManager;
    }

    @Override
    public void init(B builder) throws Exception {
        if (futureManager == null) {
            transactionManager = new DefaultNoneTransactionManager();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        builder.storeManager(new StoreProviderManager(storeProviders, transactionManager));
    }

    public StoreProviderConfigurer<B> addStoreProvider(StoreProvider storeProvider) {
        this.storeProviders.add(storeProvider);
        return this;
    }
}
