package org.itmo.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.dao.MasterDao;
import org.itmo.pojo.Master;
import org.itmo.util.SessionFactoryUtil;

import java.util.Date;

@RequiredArgsConstructor
public class MasterService {
    final MasterDao masterDao;

    public Master create(Date birthday, String name) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Master master = null;
        try {
            Transaction transaction = session.beginTransaction();
            master = new Master();
            master.setBirthday(birthday);
            master.setName(name);
            master = masterDao.save(master, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return master;
    }

    public Master show(Integer masterId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Master master = null;

        try {
            Transaction transaction = session.beginTransaction();
            master = masterDao.findById(masterId, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return master;
    }

    public void delete(Integer masterId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            masterDao.delete(masterDao.findById(masterId, session), session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
