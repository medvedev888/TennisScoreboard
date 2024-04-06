package me.vladislav.tennisscoreboard.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import me.vladislav.tennisscoreboard.models.Match;
import me.vladislav.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Player player1 = new Player("1");
            Transaction transaction1 = session.beginTransaction();
            session.save(player1);
            transaction1.commit();

            Player player2 = new Player("2");
            Transaction transaction2 = session.beginTransaction();
            session.save(player2);
            transaction2.commit();

            Match match = new Match(player1, player2, player1);
            Transaction transaction3 = session.beginTransaction();
            session.save(match);
            transaction3.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}
