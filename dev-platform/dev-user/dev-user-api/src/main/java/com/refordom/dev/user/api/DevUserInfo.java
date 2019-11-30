package com.refordom.dev.user.api;

import com.refordom.common.rpc.user.UserInfo;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/29 10:44
 */
@Data
public class DevUserInfo implements UserInfo {

    private Long id;

    private String nickname;

    private String password;

    private String mobile;

    /**
     * 认证类型
     */
    private Integer certificationType;

    /**
     * 用户状态,1为正常，0为停用，-1为锁定
     */
    private Integer status;

}
