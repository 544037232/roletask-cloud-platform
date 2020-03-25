package com.omc.builder.api;

/**
 * 事务管理器
 */
public interface TransactionManager {

    /**
     * 开始事务
     */
    void openTransaction();

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();
}
