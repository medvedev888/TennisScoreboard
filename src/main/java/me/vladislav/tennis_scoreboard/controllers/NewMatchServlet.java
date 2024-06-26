package me.vladislav.tennis_scoreboard.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import me.vladislav.tennis_scoreboard.dao.PlayerDataAccessObject;
import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;
import me.vladislav.tennis_scoreboard.utils.ValidationUtils;

import java.io.IOException;
import java.util.UUID;

import static me.vladislav.tennis_scoreboard.services.OngoingMatchesService.getInstance;

@WebServlet(name = "newMatch", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private PlayerDataAccessObject playerDataAccessObject;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        playerDataAccessObject = (PlayerDataAccessObject) context.getAttribute("playerDataAccessObject");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String player1Name = request.getParameter("player-1").strip();
        String player2Name = request.getParameter("player-2").strip();
        Player player1;
        Player player2;


        if(ValidationUtils.validateName(player1Name) && ValidationUtils.validateName(player2Name)){
            response.setStatus(HttpServletResponse.SC_OK);

            if(playerDataAccessObject.getByName(player1Name).isPresent()){
                player1 = playerDataAccessObject.getByName(player1Name).get();
            } else {
                player1 = new Player(player1Name);
                playerDataAccessObject.add(player1);
            }

            if(playerDataAccessObject.getByName(player2Name).isPresent()){
                player2 = playerDataAccessObject.getByName(player2Name).get();
            } else {
                player2 = new Player(player2Name);
                playerDataAccessObject.add(player2);
            }

            if(!player1.equals(player2)){
                UUID uuid = UUID.randomUUID();
                getInstance().addCurrentMatch(new CurrentMatchDTO(uuid, player1, player2, MatchState.IN_PROCESS));
                response.sendRedirect("view/match-score.jsp?uuid=" + uuid);
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect input parameters");
        }

    }
}
