package me.vladislav.tennisscoreboard.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import me.vladislav.tennisscoreboard.dao.PlayerDataAccessObject;
import me.vladislav.tennisscoreboard.dto.CurrentMatch;
import me.vladislav.tennisscoreboard.models.Player;
import me.vladislav.tennisscoreboard.utils.ValidationUtils;

import java.io.IOException;
import java.util.UUID;

import static me.vladislav.tennisscoreboard.services.OngoingMatchesService.getInstance;

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
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String player1Name = request.getParameter("player-1");
        String player2Name = request.getParameter("player-2");
        Player player1;
        Player player2;


        if(ValidationUtils.validateName(player1Name) && ValidationUtils.validateName(player2Name)){
            response.setStatus(HttpServletResponse.SC_OK);

            if(playerDataAccessObject.getByName(player1Name).isPresent()){
                player1 = playerDataAccessObject.getByName(player1Name).get();
            } else {
                player1 = new Player(player1Name);
            }

            if(playerDataAccessObject.getByName(player2Name).isPresent()){
                player2 = playerDataAccessObject.getByName(player2Name).get();
            } else {
                player2 = new Player(player2Name);
            }

            getInstance().addCurrentMatch(new CurrentMatch(UUID.randomUUID(), player1, player2));

            response.sendRedirect("match-score");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect input parameters");
        }

    }
}
