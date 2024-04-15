package me.vladislav.tennis_scoreboard.controllers;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vladislav.tennis_scoreboard.services.MatchScoreCalculationService;

import java.io.IOException;

@WebServlet(name="matchScore", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private MatchScoreCalculationService matchScoreCalculationService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        matchScoreCalculationService = (MatchScoreCalculationService) context.getAttribute("matchScoreCalculationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("view/match-score.jsp?uuid=" + request.getParameter("uuid")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
