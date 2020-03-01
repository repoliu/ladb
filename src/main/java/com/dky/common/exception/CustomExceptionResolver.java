package com.dky.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by liwen on 2017/2/28.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    protected static final Logger logger = null;
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {

        CustomException exception = null;
        String message = null;
        if (e instanceof CustomException) {
            exception = (CustomException) e;
        } else {
            exception = new CustomException(e);
        }
        message=exception.getMessage();
        getLogger().debug(message);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error_json");
        return null;
    }
    public Logger getLogger() {
        return LoggerFactory.getLogger(CustomException.class);
    }
}
