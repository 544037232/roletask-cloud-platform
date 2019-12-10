package com.refordom.user.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/9 17:29
 * @see Developer 开发者
 * @see CustomerAdmin 客户环境管理员
 * @see EnterpriseMember 企业成员
 * 用户身份 不包括客户端业务环境的用户
 */
public interface UserIdentity extends Serializable {

    String getPrefix();

    List<String> getRoles();
}
