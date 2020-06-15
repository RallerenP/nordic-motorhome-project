package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.MotorhomeSearchFormObject;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.DomainFacadeImpl;
import com.nordicmotorhome.motorhomerentals.domain.IDomainFacade;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Used for everything motorhome related

@Controller
@RequestMapping("/motorhomes")
public class MotorhomeController {
    private final IDomainFacade domainFacade = DomainFacadeImpl.getInstance();

    //Author : RAP
    @PostMapping("/search")
    public String searchForMotorhome(@ModelAttribute MotorhomeSearchFormObject searchObject, Model model) {
        // TODO add parse exception handling
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate start = LocalDate.parse(searchObject.getStartDate(), dtf);
        LocalDate end = LocalDate.parse(searchObject.getEndDate(), dtf);

        Message searchMessage = domainFacade.searchMotorhome(searchObject.getBeds(), start, end);

        if (searchMessage.getType() == MessageType.ERROR) return "redirect:/rentals/searchmotorhome";

        model.addAttribute("results", searchMessage.getContent());
        model.addAttribute("searchObject", searchObject);
        model.addAttribute("content", "MotorhomeSearchView.html");
        return "index";
    }
}
