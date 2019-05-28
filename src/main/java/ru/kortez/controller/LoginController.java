package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kortez.models.User;
import ru.kortez.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("login") String login, @ModelAttribute("password") String password, HttpServletRequest request) {

        System.out.println("login = " + login);
        System.out.println("password = " + password);
        User user = userService.checkUseer(login, password);
        if (user == null)
            return new ModelAndView( "index");
        else {
            request.getSession().setAttribute("user_id", user.getId());
            request.getSession().setAttribute("user_name", user.getName());
            request.getSession().setAttribute("user_surname", user.getSurname());
            ModelAndView mav = new ModelAndView("themes");
            mav.addObject("user_name", user.getName());
            mav.addObject("user_surname", user.getSurname());
            return mav;
        }
    }
}
