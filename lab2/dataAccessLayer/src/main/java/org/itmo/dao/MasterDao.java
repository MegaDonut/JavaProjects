package org.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.pojo.Master;
import org.itmo.util.SessionFactoryUtil;

import java.util.Date;

public class MasterDao {
    public Master create(Date birthday) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Master master = null;
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            master = new Master();
            master.setBirthday(birthday);
            session.persist(master);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return master;

    }

    public Master findById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Master master = null;
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            master = session.get(Master.class, id);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return master;
    }

    public void update(Master master) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(master);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void delete(Master master) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.remove(master);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
