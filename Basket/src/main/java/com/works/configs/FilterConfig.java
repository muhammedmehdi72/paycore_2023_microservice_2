package com.works.configs;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final Tracer tracer;
    final DiscoveryClient discoveryClient;

    RestTemplate restTemplate = null;
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        restTemplate = new RestTemplate();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String gotoUrl = req.getRequestURI();
        if ( gotoUrl.contains("/actuator") ) {
            filterChain.doFilter(req, res);
        }else {
            String token = req.getHeader("Authorization");
            if (token != null && !token.equals("")) {
                List<ServiceInstance> list = discoveryClient.getInstances("customer");
                if (list != null && list.size() > 0) {
                    ServiceInstance instance = list.get(0);
                    String url = instance.getUri().toString();
                    url = url + "/customer/status";
                    try {
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Authorization", token);
                        HttpEntity httpEntity = new HttpEntity("", headers);
                        restTemplate.postForEntity(url, httpEntity, Boolean.class);
                        String traceId = tracer.currentSpan().context().traceId();
                        res.setHeader("traceId", traceId);
                        filterChain.doFilter(req, res);
                    } catch (Exception ex) {
                        tokenError(req, res);
                    }
                } else {
                    tokenError(req, res);
                }
            } else {
                tokenError(req, res);
            }
        }
    }

    private void tokenError(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String errorMessage = "{ \"status\": false, \"message\": \"Token Error\" }";
        res.setStatus(HttpStatus.SC_FORBIDDEN);
        res.setContentType("application/json");
        res.getWriter().println(errorMessage);
    }

}
