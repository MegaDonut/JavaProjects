package org.itmo.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.dao.CatDao;
import org.itmo.dao.MasterDao;
import org.itmo.enums.CatBreed;
import org.itmo.enums.CatColors;
import org.itmo.pojo.Cat;
import org.itmo.util.SessionFactoryUtil;

import java.util.Date;
@RequiredArgsConstructor
public class CatService {
    final CatDao catDao;
    final MasterDao masterDao;
    public void create(String name, Date birthday, CatBreed breed, CatColors colors, Integer masterId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Cat cat = new Cat();
            cat.setName(name);
            cat.setColors(colors);
            cat.setBirthday(birthday);
            cat.setBreed(breed);
            cat.setMaster(masterDao.findById(masterId, session));
            catDao.save(cat, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    public Cat show(Integer catId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Cat cat = null;

        try {
            Transaction transaction = session.beginTransaction();
            cat = catDao.findById(catId, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return cat;
    }

    public void setFriends(Integer catIdFirst, Integer catIdSecond) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Cat catFirst = catDao.findById(catIdFirst, session);
            Cat catSecond = catDao.findById(catIdSecond, session);
            catFirst.getCats().add(catSecond);
            catSecond.getCats().add(catFirst);
            catDao.save(catFirst, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void deleteFriendship(Integer catIdFirst, Integer catIdSecond) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Cat catFirst = catDao.findById(catIdFirst, session);
            Cat catSecond = catDao.findById(catIdSecond, session);
            catFirst.getCats().remove(catSecond);
            catSecond.getCats().remove(catFirst);
            catDao.save(catFirst, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void changeMaster(Integer catId, Integer newMasterId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            Cat cat = catDao.findById(catId, session);
            cat.setMaster(masterDao.findById(newMasterId, session));
            catDao.save(cat, session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void delete(Integer catId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            catDao.delete(catDao.findById(catId, session), session);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
