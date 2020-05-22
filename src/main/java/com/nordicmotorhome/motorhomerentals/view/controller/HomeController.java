package com.nordicmotorhome.motorhomerentals.view.controller;

import com.nordicmotorhome.motorhomerentals.data.entity.Staff;
import com.nordicmotorhome.motorhomerentals.view.FormObject.LoginFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        Staff current = (Staff) session.getAttribute("user");
        if (current != null) {
            model.addAttribute("user_name", current.getFirstName());
        } else {
            model.addAttribute("user_name", "gæst");
        }

        model.addAttribute("loginObject", new LoginFormObject());

        return "index";
    }

}
