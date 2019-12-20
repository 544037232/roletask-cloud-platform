package com.refordom.app.model.enums;

/**
 * 应用类型枚举，需要对应用进行管理，可在此处扩展
 *
 * @author pricess.wang
 * @date 2019/12/12 11:01
 */
public enum AppEnum {

    /**
     * 科目应用
     */
    ACCOUNT,

    /**
     * 业务应用
     */
    BUSINESS,

    /**
     * 第三方应用
     */
    THIRD_PARTY;

    public static AppEnum valuesOf(String name) {
        for (AppEnum appEnum : AppEnum.values()) {
            if (appEnum.name().equals(name)) {
                return appEnum;
            }
        }
        return null;
    }
}
