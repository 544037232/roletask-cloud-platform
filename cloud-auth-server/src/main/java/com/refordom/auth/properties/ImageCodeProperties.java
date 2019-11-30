package com.refordom.auth.properties;

/**
 * 图片验证码配置项
 * @author : 王晟权
 * @date : 2019/6/12 11:35
 */
public class ImageCodeProperties {

    /**
     * 图片宽度
     */
    private int width = 70;
    /**
     * 图片高度
     */
    private int height = 25;

    /**
     * 验证码位数
     */
    private int length = 4;

    /**
     * 过期时间
     */
    private int expireIn = 60;

    /**
     * 需要校验的URL
     */
    private String url;

    /**
     * 是否开启
     */
    private boolean enable = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
