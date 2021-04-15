package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.domain.User;
import com.sp.SportsEventsDraw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ErrorController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }
}