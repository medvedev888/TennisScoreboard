package me.vladislav.tennis_scoreboard.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import me.vladislav.tennis_scoreboard.dao.PlayerDataAccessObject;
import me.vladislav.tennis_scoreboard.services.MatchScoreCalculationService;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        PlayerDataAccessObject playerDataAccessObject = new PlayerDataAccessObject();
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        context.setAttribute("playerDataAccessObject", playerDataAccessObject);
        context.setAttribute("matchScoreCalculationService", matchScoreCalculationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
