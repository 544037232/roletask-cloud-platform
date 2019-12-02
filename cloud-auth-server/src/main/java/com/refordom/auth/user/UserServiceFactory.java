package com.refordom.auth.user;

import cn.hutool.core.util.StrUtil;
import com.refordom.auth.exception.UserServiceException;
import com.refordom.common.rpc.user.RpcUserService;
import com.refordom.common.rpc.user.UserServiceAdapter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author pricess.wang
 * @date 2019/12/2 11:12
 */
@Component
public class UserServiceFactory {

    private final Map<String, RpcUserService> rpcUserServiceMap;

    private final Map<String, UserServiceAdapter> userServiceAdapterMap;

    public Map<String, RpcUserService> userServiceList() {
        return rpcUserServiceMap;
    }

    public UserServiceFactory() {
        Map<String, RpcUserService> rs = new HashMap<>();

        Map<String, UserServiceAdapter> adapter = new HashMap<>();

        for (UserDefaultSource userSource : UserDefaultSource.values()) {
            userSource.config();
            adapter.put(userSource.name(), userSource.userServiceAdapter());
            rs.put(userSource.name(), userSource.userService());
        }
        userServiceAdapterMap = Collections.unmodifiableMap(adapter);
        rpcUserServiceMap = Collections.unmodifiableMap(rs);
    }

    public RpcUserService getRpcService(String source) {
        if (StrUtil.isBlank(source)) {
            throw new UserServiceException("用户调用接口标识不能为空");
        }
        RpcUserService rpcUserService = rpcUserServiceMap.get(source);

        if (rpcUserService == null) {
            throw new UserServiceException("用户调用接口为空");
        }
        return rpcUserService;
    }

    public UserServiceAdapter getAdapter(String source) {
        if (StrUtil.isBlank(source)) {
            throw new UserServiceException("用户信息适配器接口标识不能为空");
        }
        UserServiceAdapter userServiceAdapter = userServiceAdapterMap.get(source);

        if (userServiceAdapter == null) {
            throw new UserServiceException("用户信息适配器接口为空");
        }
        return userServiceAdapter;
    }
}
