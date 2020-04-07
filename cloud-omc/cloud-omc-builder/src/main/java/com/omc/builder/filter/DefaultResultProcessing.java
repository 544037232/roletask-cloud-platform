package com.omc.builder.filter;

import com.omc.builder.ResultToken;
import com.omc.builder.context.ActionContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回空值对象
 */
public class DefaultResultProcessing implements ResultProcessing {

    @Override
    public ResultToken builder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResultToken resultToken = ActionContextHolder.getContext().getResult();

        if (resultToken == null){
            return new ResultToken(){};
        }
        return resultToken;
    }
}
