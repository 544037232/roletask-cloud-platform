package com.omc.builder.filter;

import com.omc.builder.ResultToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回空值对象
 */
public class ResultNullProcessing implements ResultProcessing {

    @Override
    public ResultToken builder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new ResultToken(){};
    }
}
