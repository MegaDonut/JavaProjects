package org.itmo.dao;

import org.hibernate.Session;
import org.itmo.pojo.Master;

public class MasterDao {
    public void save(Master master, Session session) {
        session.merge(master);
    }

    public Master findById(Integer id, Session session) {
        return session.get(Master.class, id);
    }

    public void delete(Master master, Session session) {
        session.remove(master);
    }
}
