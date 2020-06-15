package com.nordicmotorhome.motorhomerentals.UI.interceptors;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.LoginFormObject;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Author : RAP
//Used for deciding whether a person is logged in successfully or not, if not then return user to login screen, else homeview
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();

        StaffModel current = (StaffModel) session.getAttribute("user");

        session.setAttribute("user_name", current == null ? "gæst" : current.getFirstName());

        if (modelAndView != null) {
            modelAndView.addObject("loginObject", new LoginFormObject());

            if (current == null) {
                modelAndView.getModel().replace("content", "LoginView.html");
            }
        }
    }
}
