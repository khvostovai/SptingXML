package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.UserDao;
import ru.kortez.models.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public User getUser(int id){
        return userDao.findById(id);
    }

    @Transactional
    public User getUser(String login){
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
}
