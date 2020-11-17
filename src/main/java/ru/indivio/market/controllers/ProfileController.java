package ru.indivio.market.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.indivio.market.entites.DeliveryAddress;
import ru.indivio.market.entites.User;
import ru.indivio.market.services.DeliveryAddressService;
import ru.indivio.market.services.UsrService;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {
    private UsrService usrService;

    private DeliveryAddressService deliverAddressService;

    @Autowired
    public void setDeliverAddressService(DeliveryAddressService deliverAddressService) {
        this.deliverAddressService = deliverAddressService;
    }

    @Autowired
    public void setUserServiceImpl(UsrService usrService) {
        this.usrService = usrService;
    }

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        User user = usrService.findByName(principal.getName());
        model.addAttribute("profile", user);
        model.addAttribute("addresses",deliverAddressService.getUserAddresses(user.getId()));
        return "profile";
    }
    @PostMapping("/profile/address?{adr}")

    public String addAddress(Model model, @PathVariable(name = "adr") String address, Principal principal) {
        User user = usrService.findByName(principal.getName());
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddress(address);
        deliveryAddress.setUser(user);
        deliverAddressService.save(deliveryAddress);
        return "redirect:/profile";
    }

}
