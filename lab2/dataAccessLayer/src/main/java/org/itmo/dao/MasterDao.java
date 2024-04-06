package org.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.pojo.Master;
import org.itmo.util.SessionFactoryUtil;

public class MasterDao {
    private final Session session = SessionFactoryUtil.getSessionFactory().openSession();
    public Master save(Master master) {

        Master masterSaved = null;
        try {
            Transaction transaction = session.beginTransaction();
            masterSaved = session.merge(master);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        return masterSaved;
    }

    public Master findById(Integer id) {
        Master master = null;
        try {
            Transaction transaction = session.beginTransaction();
            master = session.get(Master.class, id);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        return master;
    }

    public void delete(Master master) {
        try {
            Transaction transaction = session.beginTransaction();
            session.remove(master);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
