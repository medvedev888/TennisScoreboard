package me.vladislav.tennisscoreboard.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import me.vladislav.tennisscoreboard.dao.PlayerDataAccessObject;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        PlayerDataAccessObject playerDataAccessObject = new PlayerDataAccessObject();

        context.setAttribute("playerDataAccessObject", playerDataAccessObject);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
