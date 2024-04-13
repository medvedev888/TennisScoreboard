package me.vladislav.tennisscoreboard.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import me.vladislav.tennisscoreboard.dao.PlayerDataAccessObject;
import me.vladislav.tennisscoreboard.services.MatchScoreCalculationService;

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
