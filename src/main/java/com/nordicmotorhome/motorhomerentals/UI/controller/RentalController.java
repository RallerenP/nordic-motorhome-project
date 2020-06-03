package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.*;
import com.nordicmotorhome.motorhomerentals.UI.model.*;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.DomainFacadeImpl;
import com.nordicmotorhome.motorhomerentals.domain.IDomainFacade;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.services.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// AUTHORS: ME, AML, NKJ, RAP
@Controller
@RequestMapping ("/rentals")
public class RentalController {

    private final IDomainFacade domainFacade = DomainFacadeImpl.getInstance();

    @GetMapping("/view/{id}")
    public String getRentalView(@PathVariable int id, HttpServletResponse request, Model model) {
        Message billMessage = domainFacade.getRental(id);

        if (billMessage.getType() == MessageType.ERROR) return "redirect:/rentals/list";

        model.addAttribute("rental", billMessage.getContent());
        model.addAttribute("content", "RentalView.html");

        return "index";
    }

    @GetMapping("/customerselect")
    public String getInitialCustomerChoiceView(HttpServletRequest request, Model model){
        request.getSession().setAttribute("rental", new AddRentalFormObject());
        model.addAttribute("content","SelectRentOptionsView.html");
        return "index";
    }

    @GetMapping("/findcustomer")
    public String getCustomerSearchView(HttpServletRequest request, Model model){
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        model.addAttribute("CPRObject", new SearchUserFormObject());
        model.addAttribute("content","SelectCustomerView.html");
        return "index";
    }

    @PostMapping("/selectcustomer")
    public String selectCustomerForRequest(HttpServletRequest request, Model model) {
        AddRentalFormObject rental = (AddRentalFormObject) request.getSession().getAttribute("rental");

        rental.setCustomerID(Integer.parseInt(request.getParameter("id")));

        // Maybe not needed
        request.getSession().setAttribute("rental", rental);

        return "redirect:/rentals/searchmotorhome";
    }

    @PostMapping("/findcustomer")
    public String searchForCustomer(@ModelAttribute SearchUserFormObject userSearchFormObject, HttpServletRequest request, Model model){

        Message searchMessage = domainFacade.findCustomer(userSearchFormObject.getCpr());

        if (searchMessage.getType() == MessageType.ERROR) return "redirect:/rentals/findcustomer";

        model.addAttribute("customer", searchMessage.getContent());
        model.addAttribute("CPRObject", userSearchFormObject);
        model.addAttribute("content","SelectCustomerView.html");
        return "index";
    }

    @GetMapping("/createcustomer")
    public String getCreateCustomerView(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        model.addAttribute( "content","RegisterCustomerView.html" );
        model.addAttribute( "customerObject",new CustomerFormObject() );
        return "index";
    }

    @GetMapping("/searchmotorhome")
    public String getMotorhomeSearchView(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        model.addAttribute("searchObject", new MotorhomeSearchFormObject());
        model.addAttribute("content","MotorhomeSearchView");
        return "index";
    }

    @GetMapping("/addaccessories")
    public String getAccessorySelectionView(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");


        Message billMessage = domainFacade.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());
        Message accessoriesMessage = domainFacade.getAllAccessories();

        if (billMessage.getType() == MessageType.ERROR) return "redirect:/rentals/searchmotorhome"; // TODO add user feedback
        if (accessoriesMessage.getType() == MessageType.ERROR || accessoriesMessage.getType() == MessageType.WARNING) model.addAttribute( "accessories", null);
        else model.addAttribute( "accessories", accessoriesMessage.getContent());

        model.addAttribute("billing", billMessage.getContent());
        model.addAttribute( "content", "RegisterAccessory.html" );
        model.addAttribute("current_accessories", arfo.getAccessoriesMap());

        return "index";
    }

    @PostMapping("/addaccessory")
    public String addAccessoryToRental(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message accessoriesMessage = domainFacade.getAccessory(id);

        if (accessoriesMessage.getType() == MessageType.ERROR) return "redirect:/rentals/addaccessories";

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToAdd = (AccessoryModel) accessoriesMessage.getContent();

        arfoAccessoryMap.put(accessoryToAdd, arfoAccessoryMap.getOrDefault(accessoryToAdd, 0) + 1);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @PostMapping("/removeaccessory")
    public String removeAccessoryFromRental(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message accessoriesMessage = domainFacade.getAccessory(id);

        if (accessoriesMessage.getType() == MessageType.ERROR) return "redirect:/rentals/addaccessories";

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToRemove = (AccessoryModel) accessoriesMessage.getContent();

        arfoAccessoryMap.put(accessoryToRemove, arfoAccessoryMap.getOrDefault(accessoryToRemove, 0) - 1);

        if (arfoAccessoryMap.get(accessoryToRemove) == 0) arfoAccessoryMap.remove(accessoryToRemove);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @GetMapping("/list")
    public String getRentalListView(Model model){
        Message rentalMessage = domainFacade.findRentals();

        if (rentalMessage.getType() != MessageType.WARNING) model.addAttribute("results", rentalMessage.getContent());
        else model.addAttribute("results", null);


        model.addAttribute("content", "RentalList.html");
        return "index";
    }

    @GetMapping("/cancelRental/{id}")
    public String cancelRental(HttpServletRequest request, @PathVariable int id){
        Message rentalMessage = domainFacade.cancelRental(id);

        if (rentalMessage.getType() == MessageType.ERROR) return "redirect:/rentals/list";

        Message accessoryMessage = domainFacade.cancelAccessoryRental(id);

        if (accessoryMessage.getType() == MessageType.ERROR) return "redirect:/rentals/list";

        return "redirect:/rentals/list";
    }

    @PostMapping("/selectmotorhome")
    public String selectMotorhomeForRental(HttpServletRequest request) {
        AddRentalFormObject rental = (AddRentalFormObject) request.getSession().getAttribute("rental");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        rental.setMotorhomeID(Integer.parseInt(request.getParameter("id")));
        rental.setStartDate(LocalDate.parse(request.getParameter("startDate"), dtf));
        rental.setEndDate(LocalDate.parse(request.getParameter("endDate"), dtf));
        // Maybe not needed
        request.getSession().setAttribute("rental", rental);

        return "redirect:/rentals/addaccessories";
    }

    @GetMapping("/finish")
    public String getIntermediateBilling(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message billMessage = domainFacade.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());

        if (billMessage.getType() == MessageType.ERROR) {
            return "redirect:/rentals/searchmotorhome"; //Todo: add user feedback
        }

        model.addAttribute("billing", billMessage.getContent());
        model.addAttribute("content", "BillingPageView.html");

        return "index";
    }

    @GetMapping("/confirm")
    public String confirmOrder(HttpServletRequest request){

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");
        Message createMessage = domainFacade.createRental(arfo.getCustomerID(), arfo.getStartDate(), arfo.getEndDate(), arfo.getMotorhomeID(),0,0);

        RentalModel rm = (RentalModel) createMessage.getContent();

        Message accessoryMessage;
        for (AccessoryModel model : arfo.getAccessoriesMap().keySet()) {
            accessoryMessage = domainFacade.createRentalAccessory(rm.getID(), model.getID(), arfo.getAccessoriesMap().get(model));
            if (accessoryMessage.getType() == MessageType.ERROR) return "redirect:/rentals/confirm";
        }

        if (createMessage.getType() == MessageType.ERROR) return "redirect:/rentals/finish"; // todo add user feedback

        return "redirect:/";
    }

    @GetMapping("/deliver/{id}")
    public String deliverMotorhome(Model model, @PathVariable int id) {
        model.addAttribute("rentalID", id);
        model.addAttribute("endRentalObject", new EndRentalFormObject());
        model.addAttribute("content", "EndRental.html");
        return "index";
    }

    @PostMapping("/deliver/{id}")
    public String submitDelivery(@ModelAttribute EndRentalFormObject endRentalObject, @PathVariable int id, Model model) {

        Message feesMessage = domainFacade.getRentalEndingFees(id, endRentalObject.isFuelNeeded(), endRentalObject.getKmDriven());

        if (feesMessage.getType() == MessageType.ERROR) return "redirect:/rentals/list";

        model.addAttribute("rentalID", id);
        model.addAttribute("endRentalObject", endRentalObject);
        model.addAttribute("result", feesMessage.getContent());

        return "redirect:/rentals/view/" + id;
    }
}
