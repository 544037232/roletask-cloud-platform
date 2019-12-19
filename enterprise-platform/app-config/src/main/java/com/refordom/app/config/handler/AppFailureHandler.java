package com.refordom.app.config.handler;

import com.refordom.app.config.exception.AppContextException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 应用接口过滤器失败处理器
 *
 * @author pricess.wang
 * @date 2019/12/17 18:01
 */
public interface AppFailureHandler {

    void onFailureContext(HttpServletRequest request, HttpServletResponse response, AppContextException contextException) throws IOException, ServletException;
}
