package com.refordom.common.data;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>执行sql过滤器，只在开发或测试环境打印sql，生产环境不打印</p>
 * {@link MybatisPlusConfig#slf4jLogFilter()}
 * @author pricess.wang
 * @date 2019/11/22 16:22
 */
public class ExecuteSqlFilter extends Slf4jLogFilter {
    private Logger sqlLogger = LoggerFactory.getLogger("SQL");

    /**
     * Must be true, otherwise {@link StatementProxy#getLastExecuteTimeNano()} doesn't work.
     *
     * @see StatementProxy#getLastExecuteTimeNano()
     */
    @Override
    public boolean isStatementLogEnabled() {
        return true;
    }

    /**
     * Must be true, otherwise {@link #statement_executeErrorAfter} never get called.
     *
     * @see #statement_executeErrorAfter
     */
    @Override
    public boolean isStatementLogErrorEnabled() {
        return true;
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean firstResult) {
        super.statementExecuteAfter(statement, sql, firstResult);
        log(statement, sql);
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
        super.statementExecuteBatchAfter(statement, result);
        String sql = statement instanceof PreparedStatementProxy ? ((PreparedStatementProxy) statement).getSql() : statement.getBatchSql();
        log(statement, sql);
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        super.statementExecuteQueryAfter(statement, sql, resultSet);
        log(statement, sql);
    }

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        super.statementExecuteUpdateAfter(statement, sql, updateCount);
        log(statement, sql);
    }

    @Override
    protected void statement_executeErrorAfter(StatementProxy statement, String sql, Throwable error) {
        super.statement_executeErrorAfter(statement, sql, error);
        log(statement, sql);
    }

    private void log(StatementProxy statement, String rawSql) {
        int elapsed = (int) statement.getLastExecuteTimeNano() / (1000 * 1000);
        String sql = String.format("use: %d ms sql: %s", elapsed, executableSql(statement, rawSql));
        sqlLogger.info(sql.replaceAll("[\\s]+", " "));
    }

    private String executableSql(StatementProxy statement, String sql) {
        if (!super.isStatementExecutableSqlLogEnable()) {
            return sql;
        }

        if (statement.getParametersSize() == 0) {
            return sql;
        }

        List<Object> parameters = new ArrayList<>(statement.getParametersSize());
        for (int i = 0; i < statement.getParametersSize(); ++i) {
            JdbcParameter jdbcParam = statement.getParameter(i);
            parameters.add(jdbcParam != null ? jdbcParam.getValue() : null);
        }

        String dbType = statement.getConnectionProxy().getDirectDataSource().getDbType();
        return SQLUtils.format(sql, dbType, parameters, super.getStatementSqlFormatOption());
    }
}
