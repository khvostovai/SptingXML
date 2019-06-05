package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
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

    @RequestMapping(value = "/themes", method = RequestMethod.GET)
    public ModelAndView themes(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @SessionAttribute(value = "user_id") int user_id) {

        User user = userService.getUser(user_id);
        ModelAndView mav = new ModelAndView("themes");
        mav.addObject("user_name", user.getName());
        mav.addObject("user_surname", user.getSurname());
        mav.addObject("user_permission", user.isPermission());
        mav.addObject("page", page);
        mav.addObject("countPages", themeService.getCountPages(pageSize));
        mav.addObject("themes", themeService.getThemesByOrder(page * pageSize, page * pageSize + pageSize));
        mav.addObject("newTheme", new Theme());
        return mav;
    }

    @RequestMapping(value = "/createTheme", method = RequestMethod.POST)
    public RedirectView createTheme(HttpServletRequest request,
                                    @ModelAttribute("newTheme") Theme newTheme,
                                    @SessionAttribute(value = "user_id") int user_id) {

        User user = userService.getUser(user_id);
        newTheme.setAuthor(user);
        themeService.addTheme(newTheme);
        messageService.addMessage(new Message("create theme", newTheme, user));
        return new RedirectView("themes");
    }

    @RequestMapping(value = "deletTheme", method = RequestMethod.GET)
    public RedirectView deleteTheme(HttpServletRequest request, HttpServletResponse response,
                                    @ModelAttribute(value = "themeId") int themeId,
                                    @SessionAttribute(value = "user_id") int user_id) {
        User user = userService.getUser(user_id);
        if(user.isPermission()) {
            themeService.removeTheme(themeService.getTheme(themeId));
            System.out.println("theme was delete");
        }
        return new RedirectView(("theme"));
    }

    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public ModelAndView theme(HttpServletRequest request,
                              HttpServletResponse response,
                              @ModelAttribute("themeId") int themeId,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (request.getSession().getAttribute("theme_id") == null)
            request.getSession().setAttribute("theme_id", themeId);
        ModelAndView mav = new ModelAndView("theme");
        Theme theme = themeService.getTheme(themeId);
        mav.addObject("page", page);
        mav.addObject("countPages", messageService.getCountPages(theme, pageSize));
        mav.addObject("messages", messageService.getMessageByTheme(theme, page * pageSize, page * pageSize + pageSize));
        mav.addObject("newMessage", new Message(theme));
        return mav;
    }
}

