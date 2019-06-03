package ru.kortez.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kortez.models.Message;
import ru.kortez.models.Theme;

import java.util.List;

@Repository
public class MessageDao {

    @Autowired
    SessionFactory sessionFactory;

    public Message findById(int id) {
        return sessionFactory.openSession().get(Message.class, id);
    }

    public void createMessage(Message message) {
        sessionFactory.openSession().save(message);
    }

    public void deleteMessage(Message message) {
        sessionFactory.openSession().delete(message);
    }

    public void updateMessage(Message message) {
        sessionFactory.openSession().update(message);
    }

    public List getMessageByTheme(Theme theme) {
        return sessionFactory.openSession().createQuery("from Message m where m.theme = :theme order by m.date desc ")
                .setParameter("theme",theme)
                .list();
    }
}
