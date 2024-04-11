<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="me.vladislav.tennisscoreboard.services.OngoingMatchesService" %>--%>
<%--<%@ page import="me.vladislav.tennisscoreboard.dto.CurrentMatch" %>--%>
<%@ page import="java.util.UUID" %>
<html>
    <head>
        <title>Match Score</title>
        <link rel="stylesheet" href="../styles/match-score-style.css" />
    </head>
    <body>
        <h1 class="heading">Match Score</h1>
        <div class="scoreboard-container">
            <div class="points-container">
                <h2>Points</h2>
            </div>
            <div class="games-container">
                <h2>Games</h2>
            </div>
            <div class="sets-container">
                <h2>Sets</h2>
            </div>
            <div class="players-container">
                <h2>Player</h2>
            </div>
            <div class="previous-sets-container">
                <h2>Previous Sets</h2>
                <div class="previous-set-container">
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-1">
                        <p>11</p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-2">
                        <p>22</p>
                    </div>
                </div>
            </div>
        </div>





<%--    <%--%>
<%--        String uuidStr = request.getParameter("uuid");--%>
<%--        UUID uuid = UUID.fromString(uuidStr);--%>
<%--        CurrentMatch currentMatch = OngoingMatchesService.getInstance().getCurrentMatch(uuid);--%>
<%--    %>--%>
<%--        <p><%= uuid %></p>--%>
<%--        <p>Player 1: <%= currentMatch.getPlayer1().getName() %> <%= currentMatch.getPlayer1().getId() %> </p>--%>
<%--        <p>Player 2: <%= currentMatch.getPlayer2().getName() %> <%= currentMatch.getPlayer2().getId() %></p>--%>
    </body>
</html>