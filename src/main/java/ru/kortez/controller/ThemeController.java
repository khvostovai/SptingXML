package ru.kortez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kortez.models.Theme;
import ru.kortez.service.MessageService;
import ru.kortez.service.ThemeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThemeController {

    @Autowired
    ThemeService themeService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/themes", method = RequestMethod.GET)
    public ModelAndView themes(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("themes");
        mav.addObject("user_name", request.getSession().getAttribute("user_name"));
        mav.addObject("user_surname", request.getSession().getAttribute("user_surname"));
        mav.addObject("themes", themeService.getAllThemes());
        return mav;
    }

    @RequestMapping(value = "/createTheme", method = RequestMethod.POST)
    public RedirectView createTheme(HttpServletRequest request){
        System.out.println(request.getAttribute("title"));
        return new RedirectView("themes");
    }

    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public ModelAndView theme(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("themeId") int themeId) {
        ModelAndView mav = new ModelAndView("theme");
        Theme theme = themeService.getTheme(themeId);
        mav.addObject("messages", theme.getMessages());
        return mav;
    }
}

