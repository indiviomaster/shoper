package ru.indivio.market.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.indivio.market.entites.DeliveryAddress;
import ru.indivio.market.entites.Role;
import ru.indivio.market.services.DeliveryAddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/address")
public class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping("")
    public String rolesPage(Model model) {
        List<DeliveryAddress> addresses = deliveryAddressService.getAll();
        model.addAttribute("addresses", addresses);
        return "addresses-page";
    }

    /*@GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        DeliveryAddress deliveryAddress = deliveryAddressService.getById(id);
        if (deliveryAddress == null) {
            deliveryAddress = new DeliveryAddress();
            deliveryAddress.setId(0L);
        }
        model.addAttribute("address", deliveryAddress);

        return "/edit-address";
    }*/

    @PostMapping("/edit")
    public String processForm(@Valid @ModelAttribute("address") DeliveryAddress deliveryAddress,  Model model) {

        deliveryAddressService.save(deliveryAddress);
        return "redirect:/address";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        deliveryAddressService.delete(id);
        return "redirect:/address";
    }

}
