<%@ page import="me.vladislav.tennis_scoreboard.dto.PaginationResultDTO" %>
<%@ page import="me.vladislav.tennis_scoreboard.models.Match" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <title>Finished Matches</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/finished-matches-style.css" />
    <script src="<%=request.getContextPath()%>/scripts/clearFilterScript.js" defer></script>
    <script src="<%=request.getContextPath()%>/scripts/placeholderHandlerScript.js" defer></script>
</head>
<%
    int numberOfPage = 1;
    Object obj = request.getAttribute("paginationResultDTO");
    PaginationResultDTO paginationResultDTO = null;
    if (obj != null) {
        paginationResultDTO = (PaginationResultDTO) obj;
        numberOfPage = paginationResultDTO.getCurrentPage();
    }
%>
<body>
    <h1 class="heading">Finished Matches</h1>
    <div class="filter-container">
        <form action="" method="get">
            <input type="text" maxlength="20" class="player" name="filter_by_player_name"
                   placeholder="Enter the name of player" value="${param.filter_by_player_name}">
            <input type="hidden" class="page" name="page" value="<%=numberOfPage%>">
            <button class="button" id="search-button" formaction="${pageContext.request.contextPath}/matches">Search</button>
            <button class="button" id="clear-button" formaction="${pageContext.request.contextPath}/matches">Clear</button>
        </form>
    </div>
    <div class="table-container">
        <table class="table">
            <thead>
            <tr>
                <th>Match ID</th>
                <th>Player 1</th>
                <th>Player 2</th>
            </tr>
            </thead>
            <tbody>
            <% for(Match match : paginationResultDTO.getListOfFinishedMatches()){ %>
            <tr>
                <th><%=match.getId()%></th>
                <th><%=match.getPlayer1().getName()%></th>
                <th><%=match.getPlayer2().getName()%></th>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="pagination-container">
        <% if(numberOfPage > 1){ %>
            <a href="${pageContext.request.contextPath}/matches?page=<%= numberOfPage - 1%>&filter_by_player_name=${param.filter_by_player_name}">Prev</a>
        <% } %>
            <div id="number-of-page-container"><p id="number-of-page"><%= numberOfPage %></p></div>
        <% if(paginationResultDTO.isHasNextPage()) { %>
            <a href="${pageContext.request.contextPath}/matches?page=<%= numberOfPage + 1%>&filter_by_player_name=${param.filter_by_player_name}">Next</a>
        <% } %>
    </div>
    <div class="link-container">
        <div class="link-subcontainer">
            <a class = "link" href="${pageContext.request.contextPath}/view/index.jsp">Back</a>
        </div>
    </div>
</body>
</html>