package ru.kortez.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.kortez.models.User;
import ru.kortez.utill.HibernateSessionFactioryUtill;

public class UserDao {
    public User findById(int id){
        return HibernateSessionFactioryUtill.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactioryUtill.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

}
