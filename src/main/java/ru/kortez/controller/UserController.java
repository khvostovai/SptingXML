package ru.kortez.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kortez.DAO.UserDao;

@Controller
public class UserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private @ResponseBody String getUsers(){
        return "hello WORLD!!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private @ResponseBody String getUser(){
        return new UserDao().findById(1).getName();
    }
}
