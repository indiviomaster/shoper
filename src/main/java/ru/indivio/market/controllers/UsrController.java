package ru.indivio.market.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import ru.indivio.market.entites.User;
import ru.indivio.market.services.UsrService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsrController {
    private UsrService usrService;

    @Autowired
    public void setUserServiceImpl(UsrService usrService) {
        this.usrService = usrService;
    }

    private final Logger logger = LoggerFactory.getLogger(UsrController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("")
    public String showUsers(Model model) {
        List<User> usr = (List<User>) usrService.findAll();
        model.addAttribute("users", usr);
        return "users-page";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model,@PathVariable(name = "id") Long id) {
        usrService.delete(id);
        return "redirect:/users";
    }
}
