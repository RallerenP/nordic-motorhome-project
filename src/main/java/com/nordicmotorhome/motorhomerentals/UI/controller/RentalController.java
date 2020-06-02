package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.*;
import com.nordicmotorhome.motorhomerentals.UI.model.*;
import com.nordicmotorhome.motorhomerentals.data.Message;
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
    CustomerService cs = new CustomerService();
    RentalService rs = new RentalService();
    MotorhomeService ms = new MotorhomeService();
    AccessoryService as = new AccessoryService();
    RentalAccessoryService ras = new RentalAccessoryService();

    @GetMapping("/view/{id}")
    public String getRentalView(@PathVariable int id, HttpServletResponse request, Model model) {
        Message billMessage = rs.getRental(id);

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
        model.addAttribute("customer", cs.findCustomer(userSearchFormObject.getCpr()));
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

    @PostMapping("/createcustomer")
    public String createCustomerForRental(@ModelAttribute CustomerFormObject customerObject, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        CustomerModel cm = cs.create( customerObject.getFirstName(), customerObject.getLastName(),customerObject.getPhone(),customerObject.getEmail(),customerObject.getCpr(),
                new StaffModel( null,null,null,null)); // TODO Actual auth

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");
        arfo.setCustomerID(cm.getID());

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/searchmotorhome";
    }

    @GetMapping("/searchmotorhome")
    public String getMotorhomeSearchView(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        model.addAttribute("searchObject", new MotorhomeSearchFormObject());
        model.addAttribute("content","MotorhomeSearchView");
        return "index";
    }

    @PostMapping("/searchmotorhome")
    public String searchForMotorhome(@ModelAttribute MotorhomeSearchFormObject searchObject, Model model) {
        // TODO add parse exception handling
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate start = LocalDate.parse(searchObject.getStartDate(), dtf);
        LocalDate end = LocalDate.parse(searchObject.getEndDate(), dtf);

        model.addAttribute("results", ms.searchMotorhomes(searchObject.getBeds(), start, end));
        model.addAttribute("searchObject", searchObject);
        model.addAttribute("content", "MotorhomeSearchView.html");
        return "index";
    }

    @GetMapping("/addaccessories")
    public String getAccessorySelectionView(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message billMessage = rs.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());

        if (billMessage.getType() == MessageType.ERROR) return "redirect:/rentals/searchmotorhome"; // TODO add user feedback

        model.addAttribute("billing", billMessage.getContent());

        model.addAttribute( "content", "RegisterAccessory.html" );
        model.addAttribute( "accessories", as.getAllAccessories());
        model.addAttribute("current_accessories", arfo.getAccessoriesMap());

        return "index";
    }

    @PostMapping("/addaccessory")
    public String addAccessoryToRental(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToAdd = as.getAccessory(id);

        arfoAccessoryMap.put(accessoryToAdd, arfoAccessoryMap.getOrDefault(accessoryToAdd, 0) + 1);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @PostMapping("/removeaccessory")
    public String removeAccessoryFromRental(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToRemove = as.getAccessory(id);

        arfoAccessoryMap.put(accessoryToRemove, arfoAccessoryMap.getOrDefault(accessoryToRemove, 0) - 1);

        if (arfoAccessoryMap.get(accessoryToRemove) == 0) arfoAccessoryMap.remove(accessoryToRemove);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @GetMapping("/list")
    public String getRentalListView(Model model){
        model.addAttribute("results", rs.findRentals());
        model.addAttribute("content", "RentalList.html");
        return "index";
    }

    @GetMapping("/cancelRental/{id}")
    public String cancelRental(HttpServletRequest request, @PathVariable int id){
        ras.cancelAccessoryRantal(id);
        rs.cancelRantal(id);
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

        Message billMessage = rs.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());

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
        Message createMessage = rs.create(arfo.getCustomerID(), arfo.getStartDate(), arfo.getEndDate(), arfo.getMotorhomeID(),0,0);

        RentalModel rm = (RentalModel) createMessage.getContent();

        for (AccessoryModel model : arfo.getAccessoriesMap().keySet()) {
            ras.create(rm.getID(), model.getID(), arfo.getAccessoriesMap().get(model));
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
    public String deliverFee(@ModelAttribute EndRentalFormObject endRentalObject, @PathVariable int id, Model model) {
        model.addAttribute("rentalID", id);
        model.addAttribute("endRentalObject", endRentalObject);
        model.addAttribute("result", rs.calculateFees(id, endRentalObject.isFuelNeeded(), endRentalObject.getKmDriven()));
        model.addAttribute("content", "EndRental.html");

        return "index";
    }
}
