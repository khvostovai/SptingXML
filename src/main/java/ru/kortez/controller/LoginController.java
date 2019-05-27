package ru.kortez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody String login(){
        return "LOGIN GET";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(Model model){
        return "LOGIN POST";
    }
}
