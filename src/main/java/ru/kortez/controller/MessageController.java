package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kortez.models.Login;
import ru.kortez.models.Message;
import ru.kortez.models.Theme;
import ru.kortez.models.User;
import ru.kortez.service.MessageService;
import ru.kortez.service.ThemeService;
import ru.kortez.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ThemeService themeService;

    //method for create messages
    @RequestMapping(value = "/createMessage", method = RequestMethod.POST)
    RedirectView createMessage(@ModelAttribute("newMessage") Message newMessage,
                               @SessionAttribute("user_id") int user_id,
                               @ModelAttribute(value = "theme_id") int theme_id) {

        User user = userService.getUser(user_id);
        newMessage.setAuthor(user);
        Theme theme = themeService.getTheme(theme_id);
        newMessage.setTheme(theme);
        newMessage.setDate(new Date());
        messageService.addMessage(newMessage);
        return new RedirectView("theme");
    }

    //for redirect to login
    @RequestMapping(value = "/createMessage", method = RequestMethod.GET)
    public RedirectView redirectView(){
        return new RedirectView("login");
    }
}
