package com.nordicmotorhome.motorhomerentals.view.interceptors;

import com.nordicmotorhome.motorhomerentals.view.FormObject.LoginFormObject;
import com.nordicmotorhome.motorhomerentals.view.model.StaffModel;
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

        session.setAttribute("user_name", current == null ? "gæst" : current.getFirstName());

//        if (modelAndView != null) {
//            modelAndView.addObject("loginObject", new LoginFormObject());
//
//            if (current == null) {
//                modelAndView.getModel().replace("content", "LoginView.html");
//            }
//        }
    }
}
