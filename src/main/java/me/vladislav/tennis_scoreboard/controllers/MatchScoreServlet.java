package me.vladislav.tennis_scoreboard.controllers;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.services.MatchScoreCalculationService;
import me.vladislav.tennis_scoreboard.services.OngoingMatchesService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        CurrentMatch currentMatch = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
        int numberOfWinnerPoint = Integer.parseInt(req.getParameter("number_of_winner_point"));
        if(numberOfWinnerPoint == 1){
            currentMatch.setCurrentPointWinner(currentMatch.getPlayer1());
        } else if(numberOfWinnerPoint == 2){
            currentMatch.setCurrentPointWinner(currentMatch.getPlayer2());
        } else {
            throw new RuntimeException("Number of winning points not correct");
        }
        matchScoreCalculationService.calculation(currentMatch);
        // проверк не закончился ли матч
        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
