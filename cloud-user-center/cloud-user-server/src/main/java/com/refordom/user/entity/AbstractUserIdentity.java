package com.refordom.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.refordom.common.data.BaseModel;

import java.util.Collections;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/9 17:40
 */
public abstract class AbstractUserIdentity extends BaseModel<AbstractUserIdentity> implements UserIdentity {

    @TableId
    private Long id;

    /**
     * 用户名，关联用户表
     */
    private String username;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<String> roles = Collections.emptyList();

    /**
     * 权限
     */
    @TableField(exist = false)
    private List<String> permissions = Collections.emptyList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

}
