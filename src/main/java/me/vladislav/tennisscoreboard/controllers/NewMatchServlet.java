package me.vladislav.tennisscoreboard.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import me.vladislav.tennisscoreboard.utils.ValidationUtils;

import java.io.IOException;

@WebServlet(name = "newMatch", value = "/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String player1 = request.getParameter("player-1");
        String player2 = request.getParameter("player-2");

        if(ValidationUtils.validateName(player1) && ValidationUtils.validateName(player2)){
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("match-score");

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect input parameters");
        }

    }
}
