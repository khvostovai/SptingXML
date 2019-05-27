package ru.kortez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThemeController {

    @RequestMapping(value = "/themes", method = RequestMethod.GET)
    public @ResponseBody
    String themes() {
        return "themes GET";
    }

    @RequestMapping(value = "/createTheme", method = RequestMethod.POST)
    public @ResponseBody String createTheme(){
        return "create Theme POST";
    }

}

