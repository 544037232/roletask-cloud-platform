package com.refordom.auth.user;

import com.refordom.common.rpc.user.RpcUserService;
import com.refordom.dev.user.api.IDevUserService;
import org.apache.dubbo.config.ReferenceConfig;

/**
 * @author pricess.wang
 * @date 2019/11/30 0:04
 */
public enum UserDefaultSource implements UserSource {

    DEV_USER {
        private IDevUserService iDevUserService;

        @Override
        public RpcUserService userService() {
            return iDevUserService;
        }

        @Override
        public void config() {
            ReferenceConfig<IDevUserService> reference = new ReferenceConfig<>();
            reference.setInterface(IDevUserService.class);
            reference.setVersion("1.0.0.0");
            reference.setGroup("dev");
            this.iDevUserService = reference.get();
        }
    }
}
