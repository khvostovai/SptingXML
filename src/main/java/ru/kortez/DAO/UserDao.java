package ru.kortez.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kortez.models.User;

@Repository
public class UserDao {

    @Autowired
    SessionFactory sessionFactory;

    public User findById(int id){
        return sessionFactory.openSession().get(User.class, id);
    }
}
