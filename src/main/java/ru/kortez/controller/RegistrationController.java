package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kortez.models.User;
import ru.kortez.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    //registration page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("registration", "user", new User());
    }

    //process registration with safe politic(password and login)
    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public RedirectView addUser(@ModelAttribute("user") User user) {
        if (userService.checkPassword(user.getPasswd()) && userService.loginFree(user.getLogin())) {
            userService.addUser(user);
            return new RedirectView("login");
        } else
            return new RedirectView("registration");
    }
}
