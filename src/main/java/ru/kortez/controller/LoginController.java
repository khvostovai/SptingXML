package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kortez.models.Login;
import ru.kortez.models.User;
import ru.kortez.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //for ajax request only
    @RequestMapping(value = "/checkLogin")
    public @ResponseBody
    String checkLogin(@RequestParam(value = "login", required = true) String login) {
        if (userService.loginFree(login))
            return "free";
        else
            return "lock";
    }

    //login page with model Login object for user date
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }

    //logout
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RedirectView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new RedirectView("login");
    }

    //method for login process, input = Login object with user data
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public RedirectView loginProcess(HttpServletRequest request, @ModelAttribute("login") Login logObj) {
        User user = userService.validateUser(logObj);
        if (user != null) {
            //load user data to session
            request.getSession().setAttribute("user_name", user.getName());
            request.getSession().setAttribute("user_surname", user.getSurname());
            request.getSession().setAttribute("user_id", user.getId());
            return new RedirectView("themes");
        }
        //if user data isn't correct
        else {
            return new RedirectView("login");
        }
    }
}
