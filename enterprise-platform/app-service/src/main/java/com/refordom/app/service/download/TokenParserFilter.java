package com.refordom.app.service.download;

import javax.servlet.*;
import java.io.IOException;

/**
 * token解析过滤器，排在最前面
 *
 * @author pricess.wang
 * @date 2020/1/16 20:53
 */
public class TokenParserFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

}
