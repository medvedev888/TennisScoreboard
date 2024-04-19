package me.vladislav.tennis_scoreboard.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vladislav.tennis_scoreboard.services.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EndCurrentMatchServlet", value = "/end-current-match")
public class EndCurrentMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatchesService.getInstance().removeCurrentMatch(uuid);
        resp.sendRedirect("view/index.jsp");
    }
}
