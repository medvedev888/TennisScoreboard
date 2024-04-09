<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="me.vladislav.tennisscoreboard.services.OngoingMatchesService" %>
<%@ page import="me.vladislav.tennisscoreboard.dto.CurrentMatch" %>
<%@ page import="java.util.UUID" %>
<html>
    <head>
        <title>Match Score</title>
    </head>
    <body>
    <%
        String uuidStr = request.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);
        CurrentMatch currentMatch = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
    %>
        <p><%= uuid %></p>
        <p>Player 1: <%= currentMatch.getPlayer1().getName() %> <%= currentMatch.getPlayer1().getId() %> </p>
        <p>Player 2: <%= currentMatch.getPlayer2().getName() %> <%= currentMatch.getPlayer2().getId() %></p>
    </body>
</html>