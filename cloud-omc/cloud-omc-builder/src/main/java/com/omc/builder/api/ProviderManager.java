package com.omc.builder.api;

import com.omc.builder.ActionMatcher;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public interface ProviderManager {

    List<Filter> getFilters();

    ActionMatcher getActionMatcher();

    void attemptExecutor(ServletRequest req, ServletResponse rep,FilterChain filterChain) throws IOException, ServletException;
}
