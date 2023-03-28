package com.works;

import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        if ( url.equals("/decrypt") ) {
            res.setStatus(HttpStatus.SC_FORBIDDEN);
            res.setContentType("application/json");
            String returnString = "{\"status\": false}";
            res.getWriter().println(returnString);
        }else {
            filterChain.doFilter(req, res);
        }

    }

}
