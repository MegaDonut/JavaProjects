package org.itmo.dao;

import org.hibernate.Session;
import org.itmo.pojo.Cat;

public class CatDao {

    public void save(Cat cat, Session session) {
        session.merge(cat);
    }

    public Cat findById(Integer id, Session session) {
        return session.get(Cat.class, id);
    }

    public void delete(Cat cat, Session session) {
        session.remove(cat);
    }
}
