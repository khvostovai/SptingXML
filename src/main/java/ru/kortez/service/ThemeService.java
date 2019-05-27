package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.ThemeDao;
import ru.kortez.models.Theme;

import java.util.List;

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
}
