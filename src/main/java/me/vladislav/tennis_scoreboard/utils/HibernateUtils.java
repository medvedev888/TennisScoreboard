package me.vladislav.tennis_scoreboard.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;

public class HibernateUtils {

    public static Session getSession() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration().configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration getConfiguration() {
        return new Configuration();
    }

    public static void fillingTheTables() {
        ClassLoader classLoader = HibernateUtils.class.getClassLoader();
        String scriptPath = classLoader.getResource("fillingTheTables.sql").getFile();
        try (Session session = getSession()) {
            session.beginTransaction();
            try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
                String line;
                StringBuilder script = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    script.append(line).append("\n");
                }

                session.createNativeQuery(script.toString()).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
