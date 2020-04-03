package com.omc.builder.manager;

import com.omc.builder.ResultToken;
import com.omc.builder.api.StoreManager;
import com.omc.builder.api.StoreProvider;
import com.omc.builder.api.TransactionManager;

import java.util.LinkedList;

public class StoreProviderManager implements StoreManager {

    private final LinkedList<StoreProvider> storeProviders;

    private final TransactionManager transactionManager;

    public StoreProviderManager(LinkedList<StoreProvider> storeProviders,TransactionManager transactionManager) {
        this.storeProviders = storeProviders;
        this.transactionManager = transactionManager;
    }

    @Override
    public void attemptExecutor(final ResultToken resultToken) {
        transactionManager.openTransaction();

        ResultToken result = resultToken;
        try {

            for (StoreProvider storeProvider : storeProviders) {

                if (storeProvider.supports(result.getClass())) {
                    result = storeProvider.provider(result);
                }
            }
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }

    }

}
