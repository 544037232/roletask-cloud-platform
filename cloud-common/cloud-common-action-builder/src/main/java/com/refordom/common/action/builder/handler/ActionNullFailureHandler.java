package com.refordom.common.action.builder.handler;

import com.refordom.common.action.builder.exception.AppContextException;

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
public class ActionNullFailureHandler implements ActionFailureHandler {

    @Override
    public void onFailureContext(HttpServletRequest request, HttpServletResponse response, AppContextException contextException) throws IOException, ServletException {

    }
}
