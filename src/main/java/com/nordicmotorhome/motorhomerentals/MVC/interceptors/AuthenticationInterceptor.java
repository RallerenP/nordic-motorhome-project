package com.nordicmotorhome.motorhomerentals.MVC.interceptors;

import com.nordicmotorhome.motorhomerentals.MVC.FormObject.LoginFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();

        StaffModel current = (StaffModel) session.getAttribute("user");

        if (current == null) {
            modelAndView.getModel().replace("content", "LoginView.html");
        }

        if (modelAndView != null) {
            modelAndView.addObject("loginObject", new LoginFormObject());
            modelAndView.addObject("user_name", current == null ? "gæst" : current.getFirstName());
        }
    }
}
