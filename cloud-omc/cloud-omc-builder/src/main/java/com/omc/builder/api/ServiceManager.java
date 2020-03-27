package com.omc.builder.api;

import com.omc.builder.ResultToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServiceManager {

    ResultToken attemptExecutor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
