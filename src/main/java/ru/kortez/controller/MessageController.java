package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import ru.kortez.models.Message;
import ru.kortez.models.Theme;
import ru.kortez.models.User;
import ru.kortez.service.MessageService;
import ru.kortez.service.ThemeService;
import ru.kortez.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ThemeService themeService;


    @RequestMapping(value = "/createMessage", method = RequestMethod.POST)
    ModelAndView createMessage(@ModelAttribute("newMessage") Message newMessage,
                               @SessionAttribute("uer_id") int user_id,
                               @SessionAttribute("theme_id") int theme_id) {

        ModelAndView mav = new ModelAndView("theme");
        User user = userService.getUser(user_id);
        newMessage.setAuthor(user);
        Theme theme = themeService.getTheme(theme_id);
        newMessage.setTheme(theme);
        newMessage.setDate(new Date());
        messageService.addMessage(newMessage);
        mav.addObject("messages", messageService.getMessageByTheme(theme, 0, 10));
        return mav;
    }
}
