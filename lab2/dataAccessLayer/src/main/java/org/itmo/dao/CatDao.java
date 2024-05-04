package org.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.pojo.Cat;
import org.itmo.util.SessionFactoryUtil;

public class CatDao {
    private final Session session = SessionFactoryUtil.getSessionFactory().openSession();

    public Cat save(Cat cat) {
        Cat catSaved = null;
        try {
            Transaction transaction = session.beginTransaction();

            catSaved = session.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        return catSaved;
    }

    public Cat findById(Integer id) {

        Cat cat = null;
        try {
            Transaction transaction = session.beginTransaction();
            cat = session.get(Cat.class, id);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        return cat;
    }

    public void delete(Cat cat) {

        try {
            Transaction transaction = session.beginTransaction();
            session.remove(cat);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
