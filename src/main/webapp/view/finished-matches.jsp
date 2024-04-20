<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Finished Matches</title>
    <script src="<%=request.getContextPath()%>/scripts/clearFilter.js" defer></script>
    <script src="<%=request.getContextPath()%>/scripts/linkContainerClickHandler.js" defer></script>
    <script src="<%=request.getContextPath()%>/scripts/placeholderHandler.js" defer></script>


</head>
<body>
    <h1 class="heading">Finished Matches</h1>
    <div class="filter-container">
        <form action="" method="get">
            <input type="text" maxlength="20" class="player" name="filter_by_player_name"
                   placeholder="Enter the name of player">
            <button class="button" id="search-button" formaction="${pageContext.request.contextPath}/matches">Search</button>
            <button class="button" id="clear-button" formaction="${pageContext.request.contextPath}/matches">Clear</button>
        </form>
    </div>
    <div class="table-container">
        <table>
            <thead>
                <th>Match ID</th>
                <th>Player 1</th>
                <th>Player 2</th>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div class="pagination-container">
        <button>Previous</button>
        <p>1</p>
        <button>Next</button>
    </div>
    <div class="link-container">
        <a class = "link" href="${pageContext.request.contextPath}/view/index.jsp">Back</a>
    </div>
    </body>
</html>
