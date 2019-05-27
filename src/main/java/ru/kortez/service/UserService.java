package ru.kortez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kortez.DAO.UserDao;
import ru.kortez.models.User;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public User getUser(int id){
        return userDao.findById(id);
    }

}
