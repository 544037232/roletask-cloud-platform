package com.refordom.auth.handler;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pricess.wang
 * @date 2019/12/2 17:22
 */
@MappedTypes({List.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ClientJdbcAuthoritiesTypeHandler extends BaseTypeHandler<List<GrantedAuthority>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<GrantedAuthority> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? "" : parameter.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
    }

    @Override
    public List<GrantedAuthority> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return authorityConvert(rs.getString(columnName));
    }

    @Override
    public List<GrantedAuthority> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return authorityConvert(rs.getString(columnIndex));
    }

    @Override
    public List<GrantedAuthority> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return authorityConvert(cs.getString(columnIndex));
    }

    private List<GrantedAuthority> authorityConvert(String values) {
        if (StrUtil.isBlank(values)){
            return new ArrayList<>();
        }
        return AuthorityUtils.createAuthorityList(values);
    }

}

