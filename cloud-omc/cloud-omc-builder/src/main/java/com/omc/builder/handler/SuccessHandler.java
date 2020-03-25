package com.omc.builder.handler;

import com.omc.builder.ResultToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface SuccessHandler {

    void onSuccessContext(HttpServletRequest request, HttpServletResponse response, ResultToken resultToken) throws IOException, ServletException;

}
