package com.refordom.app.config.handler;

import com.refordom.app.config.AppToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 应用接口过滤器执行成功处理
 *
 * @author pricess.wang
 * @date 2019/12/17 18:01
 */
public interface AppSuccessHandler {

    void onSuccessContext(HttpServletRequest request, HttpServletResponse response, AppToken appToken) throws IOException, ServletException;
}
