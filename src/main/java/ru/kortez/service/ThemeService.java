package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.ThemeDao;
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
    public void removeTheme(int id) {
        Theme theme = getTheme(id);
        if (theme != null)
            themeDao.delete(theme);
    }

    @Transactional
    public List getAllThemes() {
        return themeDao.findAll();
    }

    @Transactional
    public List getThemesByOrder(int first, int pageSize) {
        List<Object[]> result = themeDao.byOrder(first, pageSize);
        Map<String, Object> themes = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] o : result) {
            Map<String, Object> theme = new HashMap<>();
            theme.put("id", o[0]);
            theme.put("title", o[1]);
            theme.put("date", o[2]);
            resultList.add(theme);
        }
        return resultList;
    }

    @Transactional
    public int getCountPages(int pageSize) {
        return (int) Math.ceil(themeDao.getCountOfThemes() / pageSize);
    }
}
