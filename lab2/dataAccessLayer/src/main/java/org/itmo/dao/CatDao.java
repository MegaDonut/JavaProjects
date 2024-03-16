package org.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.itmo.pojo.Cat;
import org.itmo.enums.CatBreed;
import org.itmo.enums.CatColors;
import org.itmo.pojo.Master;
import org.itmo.util.SessionFactoryUtil;

import java.util.Date;

public class CatDao {
    public Cat create(String name, Date birthday, CatBreed breed, CatColors colors, Master master) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Cat cat = null;
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            cat = new Cat();
            cat.setName(name);
            cat.setBirthday(birthday);
            cat.setBreed(breed);
            cat.setColors(colors);
            cat.setMaster(master);
            session.persist(cat);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return cat;
    }

    public Cat findById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();;
        Cat cat = null;
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            cat = session.get(Cat.class, id);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return cat;
    }

    public void update(Cat cat) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.merge(cat);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void delete(Cat cat) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.remove(cat);
            transaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
