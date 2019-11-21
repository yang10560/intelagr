package com.oracle.intelagr.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @拦截器类
 */
public class CommonInterceptor implements HandlerInterceptor {
    private String[] allowURLs;

    public String[] getAllowURLs() {
        return allowURLs;
    }

    public void setAllowURLs(String[] allowURLs) {
        this.allowURLs = allowURLs;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        for (String url : allowURLs) {
            if (path.contains(url)) {
                return true;
            }
        }

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
