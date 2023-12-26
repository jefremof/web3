package ru.jefremov.prog;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure()
                    .addAnnotatedClass(ResultBean.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Something went wrong during initializing Hibernate: " + e);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}