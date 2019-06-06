package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.UserDao;
import ru.kortez.models.Login;
import ru.kortez.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User getUser(int id) {
        return userDao.findById(id);
    }

    @Transactional
    public User getUser(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    public void addUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public void removeUser(User user) {
        userDao.deletUser(user);
    }

    @Transactional
    public List getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public User validateUser(Login login) {
        User user = userDao.findByLogin(login.getLogin());
        if (user == null) return null;
        if (!user.getPasswd().equals(login.getPassword())) return null;
        return user;
    }

    public boolean checkPassword(String password) {
        Pattern digit = Pattern.compile("[0-9]");
        Pattern upper = Pattern.compile("[A-Z]");
        Pattern lower = Pattern.compile("[a-z]");
        Pattern symbol = Pattern.compile("[!@#$%]");

        boolean digitMatcher = digit.matcher(password).find();
        boolean upperMatcher = upper.matcher(password).find();
        boolean lowerMatcher = lower.matcher(password).find();
        boolean symbolMatcher = symbol.matcher(password).find();

        return (password.length() > 8) && digitMatcher && upperMatcher && lowerMatcher && symbolMatcher;
    }

    @Transactional
    public boolean loginFree(String login) {
        return (userDao.findByLogin(login) == null);
    }
}
