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
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThemeController {

    @Autowired
    ThemeService themeService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    //main page, it show list of themes with pagination(default 10 on page)
    @RequestMapping(value = "/themes", method = RequestMethod.GET)
    public ModelAndView themes(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @SessionAttribute(value = "user_id") int user_id) {
        //for new session
        if (request.getSession().isNew())
            return new ModelAndView("login", "login", new Login());
        //for other
        User user = userService.getUser(user_id);
        ModelAndView mav = new ModelAndView("themes");
//        mav.addObject("user_name", user.getName());
//        mav.addObject("user_surname", user.getSurname());
        mav.addObject("user_permission", user.isPermission());
        mav.addObject("page", page);
        mav.addObject("countPages", themeService.getCountPages(pageSize));
        mav.addObject("themes", themeService.getThemesByOrder(page * pageSize, page * pageSize + pageSize));
        //model for new theme
        mav.addObject("newTheme", new Theme());
        return mav;
    }

    //process creating theme
    @RequestMapping(value = "/createTheme", method = RequestMethod.POST)
    public RedirectView createTheme(HttpServletRequest request,
                                    @ModelAttribute("newTheme") Theme newTheme,
                                    @SessionAttribute(value = "user_id", required = true) int user_id) {
        //for new session
        if (request.getSession().isNew())
            return new RedirectView("login");
        //for other create theme
        User user = userService.getUser(user_id);
        newTheme.setAuthor(user);
        themeService.addTheme(newTheme);
        messageService.addMessage(new Message("create theme", newTheme, user));
        //refresh page
        return new RedirectView("themes");
    }

    @RequestMapping(value = "deletTheme", method = RequestMethod.GET)
    public RedirectView deleteTheme(HttpServletRequest request, HttpServletResponse response,
                                    @ModelAttribute(value = "theme_id") int theme_id,
                                    @SessionAttribute(value = "user_id", required = true) int user_id) {
        //for new session
        if (request.getSession().isNew())
            return new RedirectView("login");

        //for other delete theme if permision is true
        User user = userService.getUser(user_id);
        if (user.isPermission()) {
            themeService.removeTheme(theme_id);
        }
        //refresh page
        return new RedirectView("themes");
    }

    //for show messages of themes with pagination(default 10 on page)
    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public ModelAndView theme(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "theme_id") int theme_id,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //for new session
        if (request.getSession().isNew())
            return new ModelAndView("login", "login", new Login());


        if (request.getSession().getAttribute("theme_id") == null) {
            request.getSession().setAttribute("theme_id", theme_id);
        }
        else
            theme_id = (int) request.getSession().getAttribute("theme_id");
        ModelAndView mav = new ModelAndView("theme");
        Theme theme = themeService.getTheme(theme_id);
        mav.addObject("page", page);
        mav.addObject("countPages", messageService.getCountPages(theme, pageSize));
        mav.addObject("messages", messageService.getMessageByTheme(theme, page * pageSize, page * pageSize + pageSize));
        mav.addObject("newMessage", new Message(theme));
        return mav;
    }
}

