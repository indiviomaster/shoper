package ru.indivio.market.controllers;

import ru.indivio.market.entites.SystemUser;
import ru.indivio.market.entites.User;
import ru.indivio.market.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.indivio.market.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }
    @GetMapping("/showAll")
    public String showAll(Model model) {

        model.addAttribute("users", (List<User>)userService.findAll());
        return "/users-page";
    }
    @GetMapping("/show/{id}")
    public String showAll(Model model,@PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "/profile";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser, BindingResult theBindingResult, Model model) {
        String userName = theSystemUser.getUserName();
        logger.debug("Processing registration form for: " + userName);
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            //theSystemUser.setUserName(null);
            model.addAttribute("systemUser", theSystemUser);
            model.addAttribute("registrationError", "User with current username already exists");
            logger.debug("User name already exists.");
            return "registration-form";
        }
        userService.save(theSystemUser);
        logger.debug("Successfully created user: " + userName);
        //return "registration-confirmation";
        return "redirect:/login";
    }
}
