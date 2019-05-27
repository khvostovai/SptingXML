package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kortez.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userServise;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private @ResponseBody String getUsers(){
        return "hello WORLD!!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private @ResponseBody String getUser(){
        return userServise.getUser(1).getLogin();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private @ResponseBody String getList() {
        System.out.println(userServise.getAllUsers());
        return "List";
    }
}
