package me.vladislav.tennis_scoreboard.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vladislav.tennis_scoreboard.dao.MatchDataAccessObject;
import me.vladislav.tennis_scoreboard.dto.PaginationResultDTO;
import me.vladislav.tennis_scoreboard.services.FinishedMatchesPersistenceService;
import me.vladislav.tennis_scoreboard.services.MatchScoreCalculationService;

import java.io.IOException;

@WebServlet(name = "finishedMatches", value = "/matches")
public class FinishedMatchesServlet extends HttpServlet {
    private MatchDataAccessObject matchDataAccessObject;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) context.getAttribute("finishedMatchesPersistenceService");
        matchDataAccessObject = (MatchDataAccessObject) context.getAttribute("matchDataAccessObject");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = Integer.parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        PaginationResultDTO paginationResultDTO = finishedMatchesPersistenceService.getListOfMatchesByPlayerNameForPage(page, playerName, matchDataAccessObject);
        request.setAttribute("paginationResultDTO", paginationResultDTO);
        request.getRequestDispatcher("view/finished-matches.jsp").forward(request, response);
    }
}
