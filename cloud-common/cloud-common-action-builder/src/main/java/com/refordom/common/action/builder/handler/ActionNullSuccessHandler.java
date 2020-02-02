package com.refordom.common.action.builder.handler;

import com.refordom.common.action.builder.ResultToken;

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
public class ActionNullSuccessHandler implements ActionSuccessHandler{

    @Override
    public void onSuccessContext(HttpServletRequest request, HttpServletResponse response, ResultToken resultToken) throws IOException, ServletException {

    }
}
