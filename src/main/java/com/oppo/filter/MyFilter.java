package com.oppo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by JieChen on 2018/7/30.
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("Filter.....");
    }

    @Override
    public void destroy() {

    }
}
