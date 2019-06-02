package ru.kortez.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kortez.models.Theme;

import java.util.List;

@Repository
public class ThemeDao {

    @Autowired
    SessionFactory sessionFactory;

    public Theme findById(int id) {
        return sessionFactory.openSession().get(Theme.class, id);
    }

    public void create(Theme theme) {
        sessionFactory.openSession().save(theme);
    }

    public void delete(Theme theme) {
        sessionFactory.openSession().delete(theme);
    }

    public List findAll() {
        return sessionFactory.openSession().createQuery("from Theme").list();
    }

    public List byOrder() {
        Query query = sessionFactory.openSession().createSQLQuery("select t.title, x.minDate from \n" +
                "(select theme_id, max(date) as minDate from messages group by theme_id)\n" +
                "as x inner join themes as t on t.id = x.theme_id");
        return query.list();
    }
}