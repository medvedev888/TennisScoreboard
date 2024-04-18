<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Currency" %>
<%@ page import="me.vladislav.tennis_scoreboard.dto.CurrentMatch" %>
<%@ page import="me.vladislav.tennis_scoreboard.services.OngoingMatchesService" %>
<%@ page import="me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState" %>

<%
    String uuidString = request.getParameter("uuid");
    UUID uuid = UUID.fromString(uuidString);
    CurrentMatch currentMatch = OngoingMatchesService.getInstance().getCurrentMatch(uuid);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Match Score</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/match-score-style.css" />
        <script src="<%=request.getContextPath()%>/scripts/linkContainerClickHandler.js" defer></script>
    </head>
    <body>
        <h1 class="heading">Match Score</h1>
        <div class="scoreboard-container">
            <% if (currentMatch.getMatchState() != MatchState.PLAYER_1_WON &&
                    currentMatch.getMatchState() != MatchState.PLAYER_2_WON) { %>
            <div class="buttons-container" id="container-1">
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString() %>&number_of_winner_point=1" method="post">
                    <button class="button" name="button-player-1-win-a-point" value="player-1-win">Player 1<br>wins<br>a point</button>
                </form>
                <form action="${pageContext.request.contextPath}/match-score?uuid=<%= uuid.toString()%>&number_of_winner_point=2" method="post">
                    <button class="button" name="button-player-2-win-a-point" value="player-2-win">Player 2<br>wins<br>a point</button>
                </form>
            </div>
            <% } %>
            <div class="points-container">
                <p>Points</p>
                <div class="subelement">
                    <p><%= currentMatch.getGameScoreOfPlayer1().getPointValue() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatch.getGameScoreOfPlayer2().getPointValue() %></p>
                </div>
            </div>
            <div class="games-container">
                <p>Games</p>
                <div class="subelement">
                    <p><%= currentMatch.getSetScoreOfPlayer1() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatch.getSetScoreOfPlayer2() %></p>
                </div>
            </div>
            <div class="sets-container">
                <p>Sets</p>
                <div class="subelement">
                    <p><%= currentMatch.getMatchScoreOfPlayer1() %></p>
                </div>
                <div class="subelement">
                    <p><%= currentMatch.getMatchScoreOfPlayer2() %></p>
                </div>
            </div>
            <div class="players-container">
                <p>Player</p>
                <div class="subelement" id="subelement_player_1">
                    <p>
                        <%= currentMatch.getPlayer1().getName() %>
                        <% if (currentMatch.getMatchState() == MatchState.PLAYER_1_WON) { %>
                        <span class="crown-icon">&#9812;</span>
                        <% } %>
                    </p>
                </div>
                <div class="subelement" id="subelement_player_2">
                    <p>
                        <%= currentMatch.getPlayer2().getName() %>
                        <% if (currentMatch.getMatchState() == MatchState.PLAYER_2_WON) { %>
                        <span class="crown-icon">&#9812;</span>
                        <% } %>
                    </p>
                </div>
            </div>
            <div class="previous-sets-container">
                <p>Previous Sets</p>
                <div class="previous-set-container">
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-1">
                        <p><%= currentMatch.getPreviousSet1ScoreOfPlayer1() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-2">
                        <p><%= currentMatch.getPreviousSet2ScoreOfPlayer1() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-3">
                        <p><%= currentMatch.getPreviousSet1ScoreOfPlayer2() %></p>
                    </div>
                    <div class="previous-set-subcontainer" id="previous-set-subcontainer-4">
                        <p><%= currentMatch.getPreviousSet2ScoreOfPlayer2() %></p>
                    </div>
                </div>
            </div>

        </div>
        <% if (currentMatch.getMatchState() == MatchState.PLAYER_1_WON ||
                currentMatch.getMatchState() == MatchState.PLAYER_2_WON) { %>
        <div class="link-container">
            <a class = "link" href="${pageContext.request.contextPath}/view/index.jsp">Continue</a>
        </div>
        <% } %>
    </body>
</html>