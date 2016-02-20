package com.hoon.cmd;

import com.hoon.cmd.service.security.CmdUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by hoon on 2016-02-20.
 */
public class CmdInterceptor extends WebContentInterceptor {

    @Autowired
    private CmdUserHolder cmdUserHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView == null) {
            return;
        }
        //addMenuData(request, modelAndView);
        addLocaleInfo(request, modelAndView);
        addUserData(modelAndView);
        addProjectName(modelAndView);
    }

    void addLocaleInfo(HttpServletRequest request, ModelAndView modelAndView) {
        Locale locale = RequestContextUtils.getLocale(request);
        modelAndView.addObject("locale", locale.getLanguage());
    }

    void addUserData(ModelAndView modelAndView) {
        modelAndView.addObject("cmdUser", cmdUserHolder.getCmdUser());
    }

    void addProjectName(ModelAndView modelAndView) {
        modelAndView.addObject("projectName", "CMD Project");
    }
}
