package com.omc.builder.global.transaction;

import com.omc.builder.api.TransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JdbcTransactionManager implements TransactionManager {

    /**
     * 事务管理器
     */
    private final PlatformTransactionManager transactionManager;

    /**
     * 事务传播机制
     */
    private final DefaultTransactionDefinition transDefinition;

    private ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<>();

    public JdbcTransactionManager(PlatformTransactionManager transactionManager, DefaultTransactionDefinition transDefinition) {
        this.transactionManager = transactionManager;
        this.transDefinition = transDefinition;
    }

    @Override
    public void openTransaction() {
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);

        transactionStatus.set(transStatus);
    }

    @Override
    public void commit() {
        TransactionStatus status = transactionStatus.get();

        if (status != null) {
            transactionManager.commit(status);
            transactionStatus.remove();
        }
    }

    @Override
    public void rollback() {
        TransactionStatus status = transactionStatus.get();

        if (status != null) {
            transactionManager.rollback(status);
            transactionStatus.remove();
        }
    }
}
