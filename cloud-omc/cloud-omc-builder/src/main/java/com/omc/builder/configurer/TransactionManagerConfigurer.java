package com.omc.builder.configurer;

import com.omc.builder.global.future.FutureBuilder;
import com.omc.builder.global.transaction.DefaultNoneTransactionManager;
import com.omc.builder.global.transaction.JdbcTransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

public class TransactionManagerConfigurer<B extends FutureBuilder<B>>
        extends AbstractManagerConfigurer<TransactionManagerConfigurer<B>, B> {

    private final ApplicationContext context;

    private TransactionBuilder<B> transactionBuilder;

    public TransactionManagerConfigurer(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void init(B builder) throws Exception {
        if (transactionBuilder == null) {
            transactionBuilder = new DefaultTransactionBuilder();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        transactionBuilder.builder(builder);
    }

    public JdbcTransaction jdbc() {
        JdbcTransaction jdbcTransaction = new JdbcTransaction(context, this);
        this.transactionBuilder = jdbcTransaction;
        return jdbcTransaction;
    }

    private class DefaultTransactionBuilder implements TransactionBuilder<B> {
        @Override
        public void builder(B builder) {
            builder.transactionManager(new DefaultNoneTransactionManager());
        }
    }

    private interface TransactionBuilder<B> {
        void builder(B builder);
    }

    public class JdbcTransaction implements TransactionBuilder<B> {
        /**
         * 事务管理器
         */
        private final PlatformTransactionManager transactionManager;

        /**
         * 事务传播机制
         */
        private DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);

        private TransactionManagerConfigurer<B> configurer;

        public JdbcTransaction(ApplicationContext context, TransactionManagerConfigurer<B> configurer) {
            this.configurer = configurer;

            DataSource dataSource = context.getBean(DataSource.class);
            this.transactionManager = new DataSourceTransactionManager(dataSource);
        }

        public JdbcTransaction definition(int propagationBehavior) {
            transDefinition.setPropagationBehavior(propagationBehavior);
            return this;
        }

        public TransactionManagerConfigurer<B> and() {
            return configurer;
        }

        @Override
        public void builder(B builder) {
            builder.transactionManager(new JdbcTransactionManager(transactionManager, transDefinition));
        }

    }

}
