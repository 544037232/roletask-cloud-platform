package com.refordom.common.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * <p>数据对象实体公共字段属性</p>
 *
 * @author pricess.wang
 * @date 2019/9/18 10:27
 */
public class BaseModel<T extends Model<?>> extends Model<T> {

    /**
     * 创建时间
     */
    @TableField(value = "crt_time",fill = FieldFill.INSERT)
    private LocalDateTime crtTime;

    /**
     * 创建者,本字段不建议存储ID，仅用作追查，若对追溯级别要求比较高，可存储具体ID，以防止改名或重名而不知道是哪个创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 修改时间
     */
    @TableField(value = "mod_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modTime;

    /**
     * 修改者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    public LocalDateTime getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(LocalDateTime crtTime) {
        this.crtTime = crtTime;
    }

    public LocalDateTime getModTime() {
        return modTime;
    }

    public void setModTime(LocalDateTime modTime) {
        this.modTime = modTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
