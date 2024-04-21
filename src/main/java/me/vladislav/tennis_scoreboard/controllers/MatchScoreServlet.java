package me.vladislav.tennis_scoreboard.controllers;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vladislav.tennis_scoreboard.dao.MatchDataAccessObject;
import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.services.FinishedMatchesPersistenceService;
import me.vladislav.tennis_scoreboard.services.MatchScoreCalculationService;
import me.vladislav.tennis_scoreboard.services.OngoingMatchesService;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameResult;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;
import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetResult;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name="matchScore", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private MatchScoreCalculationService matchScoreCalculationService;
    private MatchDataAccessObject matchDataAccessObject;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        matchScoreCalculationService = (MatchScoreCalculationService) context.getAttribute("matchScoreCalculationService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) context.getAttribute("finishedMatchesPersistenceService");
        matchDataAccessObject = (MatchDataAccessObject) context.getAttribute("matchDataAccessObject");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("view/match-score.jsp?uuid=" + request.getParameter("uuid")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        CurrentMatchDTO currentMatchDTO = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
        int numberOfWinnerPoint = Integer.parseInt(req.getParameter("number_of_winner_point"));
        if(numberOfWinnerPoint == 1){
            currentMatchDTO.setCurrentPointWinner(currentMatchDTO.getPlayer1());
        } else if(numberOfWinnerPoint == 2){
            currentMatchDTO.setCurrentPointWinner(currentMatchDTO.getPlayer2());
        } else {
            throw new RuntimeException("Number of winning points not correct");
        }
        matchScoreCalculationService.calculation(currentMatchDTO);
        if(currentMatchDTO.getMatchState() == MatchState.PLAYER_1_WON || currentMatchDTO.getMatchState() == MatchState.PLAYER_2_WON){
            matchScoreCalculationService.setGameResult(GameResult.IN_PROCESS);
            matchScoreCalculationService.setSetResult(SetResult.IN_PROCESS);

            finishedMatchesPersistenceService.saveMatch(currentMatchDTO, matchDataAccessObject);
        }
        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}
