package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.ThemeDao;
import ru.kortez.DAO.ThemeResult;
import ru.kortez.models.Theme;

import java.util.*;

@Service
public class ThemeService {

    @Autowired
    ThemeDao themeDao;

    @Transactional
    public Theme getTheme(int id) {
        return themeDao.findById(id);
    }

    @Transactional
    public void addTheme(Theme theme) {
        themeDao.create(theme);
    }

    @Transactional
    public void removeTheme(Theme theme) {
        themeDao.delete(theme);
    }

    @Transactional
    public List getAllThemes() {
        return themeDao.findAll();
    }

    @Transactional
    public List getThemesByOrder(){
        Map<String, Object> themes = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Object[]> result = themeDao.byOrder();
        for (Object[] o : result) {
            Map<String, Object> theme = new HashMap<>();
            theme.put("id", o[0]);
            theme.put("title", o[1]);
            theme.put("date", o[2]);
            resultList.add(theme);
        }
        return resultList;
    }


    public List getThemesByPage(int page) {
        List themes = getAllThemes();
        //10 themes at page
        if (themes.size() < 10) {
            return themes;
        } else {
            themes = themes.subList(10 * page, 10 * (page + 1));
            return themes;
        }
    }

}
