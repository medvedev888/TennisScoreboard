package me.vladislav.tennisscoreboard.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static Session getSession(){
        try (SessionFactory sessionFactory = getSessionFactory();
             Session session = sessionFactory.openSession()){
            return session;
        }
    }

    public static SessionFactory getSessionFactory(){
        Configuration configuration = getConfiguration().configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration getConfiguration(){
        return new Configuration();
    }
}
