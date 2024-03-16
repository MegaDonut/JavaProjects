package org.itmo.util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    @Getter
    private final static SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();
}
