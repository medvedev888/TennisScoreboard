package me.vladislav.tennisscoreboard.controllers;

import java.io.IOException;

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

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Создаем первого игрока и сохраняем его в отдельной транзакции
            Player player1 = new Player("1");
            Transaction transaction1 = session.beginTransaction();
            session.save(player1);
            transaction1.commit();

            // Создаем второго игрока и сохраняем его в отдельной транзакции
            Player player2 = new Player("2");
            Transaction transaction2 = session.beginTransaction();
            session.save(player2);
            transaction2.commit();

            // Создаем матч между первым и вторым игроками и сохраняем его в отдельной транзакции
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
