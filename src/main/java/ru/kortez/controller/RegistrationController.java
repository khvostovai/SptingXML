package ru.kortez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public @ResponseBody String registration(){
        return "registration GET";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody String registration(Model model){
        return "registration POST";
    }
}
