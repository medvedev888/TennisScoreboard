<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Currency" %>
<%@ page import="me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO" %>
<%@ page import="me.vladislav.tennis_scoreboard.services.OngoingMatchesService" %>
<%@ page import="me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState" %>

<%
    String uuidString = request.getParameter("uuid");
    UUID uuid = UUID.fromString(uuidString);
    CurrentMatchDTO currentMatchDTO = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Match Score</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/match-score-style.css" />
    </head>
    <body>
        <h1 class="heading">Match Score</h1>
        <div class="scoreboard-container">
            <% if (currentMatchDTO.getMatchState() != MatchState.PLAYER_1_WON &&
                    currentMatchDTO.getMatchState() != MatchState.PLAYER_2_WON) { %>
            <div class="buttons-container" id="container-1">
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString() %>&number_of_winner_point=1" method="post">
                    <button class="button">Player 1<br>wins<br>a point</button>
                </form>
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString()%>&number_of_winner_point=2" method="post">
                    <button class="button">Player 2<br>wins<br>a point</button>
                </form>
            </div>
            <% } %>
            <div class="points-container">
                <p>Points</p>
                <div class="subelement">
                    <p><%= currentMatchDTO.getGameScoreOfPlayer1().getPointValue() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatchDTO.getGameScoreOfPlayer2().getPointValue() %></p>
                </div>
            </div>
            <div class="games-container">
                <p>Games</p>
                <div class="subelement">
                    <p><%= currentMatchDTO.getSetScoreOfPlayer1() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatchDTO.getSetScoreOfPlayer2() %></p>
                </div>
            </div>
            <div class="sets-container">
                <p>Sets</p>
                <div class="subelement">
                    <p><%= currentMatchDTO.getMatchScoreOfPlayer1() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatchDTO.getMatchScoreOfPlayer2() %></p>
                </div>
            </div>
            <div class="players-container">
                <p>Player</p>
                <div class="subelement" id="subelement_player_1">
                    <p>
                        <%= currentMatchDTO.getPlayer1().getName() %>
                        <% if (currentMatchDTO.getMatchState() == MatchState.PLAYER_1_WON) { %>
                        <span class="crown-icon">&#9812;</span>
                        <% } %>
                    </p>
                </div>
                <div class="subelement" id="subelement_player_2">
                    <p>
                        <%= currentMatchDTO.getPlayer2().getName() %>
                        <% if (currentMatchDTO.getMatchState() == MatchState.PLAYER_2_WON) { %>
                        <span class="crown-icon">&#9812;</span>
                        <% } %>
                    </p>
                </div>
            </div>
            <div class="previous-sets-container">
                <p>Previous Sets</p>
                <div class="previous-set-container">
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-1">
                        <p><%= currentMatchDTO.getPreviousSet1ScoreOfPlayer1() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-2">
                        <p><%= currentMatchDTO.getPreviousSet2ScoreOfPlayer1() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-3">
                        <p><%= currentMatchDTO.getPreviousSet1ScoreOfPlayer2() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-4">
                        <p><%= currentMatchDTO.getPreviousSet2ScoreOfPlayer2() %></p>
                    </div>
                </div>
            </div>

        </div>
        <% if (currentMatchDTO.getMatchState() == MatchState.PLAYER_1_WON ||
                currentMatchDTO.getMatchState() == MatchState.PLAYER_2_WON) { %>
        <form action="${pageContext.request.contextPath}/end-current-match" method="get">
            <input type="hidden" name="uuid" value="<%= currentMatchDTO.getId().toString() %>">
            <button class="button" id="continue-button">Continue</button>
        </form>
        <% } %>
    </body>
</html>