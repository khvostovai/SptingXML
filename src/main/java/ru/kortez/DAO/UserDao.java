package ru.kortez.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kortez.models.User;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    SessionFactory sessionFactory;

    public User findById(int id){
        return sessionFactory.openSession().get(User.class, id);
    }

    public User findByLogin(String login) {
        Query query = sessionFactory.openSession().createQuery("" +
                "from User u where u.login=:userLogin");
        query.setParameter("userLogin", login);
        List results = query.list();
        if (results.size()!=0)
            return (User) results.get(0);
        return null;
    }

    public void createUser(User user) {
        sessionFactory.openSession().save(user);
    }

    public void deletUser(User user) {
        sessionFactory.openSession().delete(user);
    }

    public List findAll() {
        return sessionFactory.openSession().createQuery("from User").list();
    }
}
