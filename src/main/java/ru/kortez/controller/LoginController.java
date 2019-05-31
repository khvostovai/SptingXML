package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kortez.models.Login;
import ru.kortez.models.User;
import ru.kortez.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public RedirectView loginProcess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {
        RedirectView rv = null;
        User user = userService.validateUser(login);
        if (user != null) {
            request.getSession().setAttribute("user_name", user.getName());
            request.getSession().setAttribute("user_surname", user.getSurname());
            rv = new RedirectView("themes");
        }
        else {
            rv = new RedirectView("login");
        }
        return rv;
    }
}
