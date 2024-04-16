<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Currency" %>
<%@ page import="me.vladislav.tennis_scoreboard.dto.CurrentMatch" %>
<%@ page import="me.vladislav.tennis_scoreboard.services.OngoingMatchesService" %>

<%
    String uuidString = request.getParameter("uuid");
    UUID uuid = UUID.fromString(uuidString);
    CurrentMatch currentMatch = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
%>

<html>
    <head>
        <title>Match Score</title>
        <link rel="stylesheet" href="../styles/match-score-style.css" />
    </head>
    <body>
        <h1 class="heading">Match Score</h1>
        <div class="scoreboard-container">
            <div class="buttons-container" id="container-1">
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString() %>&number_of_winner_point=1" method="post">
                    <button class="button" name="button-player-1-win-a-point" value="player-1-win">Player 1<br>wins<br>a point</button>
                </form>
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString()%>&number_of_winner_point=2" method="post">
                    <button class="button" name="button-player-2-win-a-point" value="player-2-win">Player 2<br>wins<br>a point</button>
                </form>
            </div>
            <div class="points-container">
                <p>Points</p>
                <div class="subelement">
                    <p>1</p>
                </div>
                <div class="subelement">
                    <p>4</p>
                </div>
            </div>
            <div class="games-container">
                <p>Games</p>
                <div class="subelement">
                    <p>3</p>
                </div>
                <div class="subelement">
                    <p>4</p>
                </div>
            </div>
            <div class="sets-container">
                <p>Sets</p>
                <div class="subelement">
                    <p>3</p>
                </div>
                <div class="subelement">
                    <p>4</p>
                </div>
            </div>
            <div class="players-container">
                <p>Player</p>
                <div class="subelement" id="subelement_player_1">
                    <p><%= currentMatch.getPlayer1().getName()%></p>
                </div>
                <div class="subelement" id="subelement_player_2">
                    <p><%= currentMatch.getPlayer2().getName()%></p>
                </div>
            </div>
            <div class="previous-sets-container">
                <p>Previous Sets</p>
                <div class="previous-set-container">
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-1">
                        <p>11</p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-2">
                        <p>22</p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-3">
                        <p>33</p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-4">
                        <p>44</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>