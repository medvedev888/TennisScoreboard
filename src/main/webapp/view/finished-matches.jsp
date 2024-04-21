<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Finished Matches</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/finished-matches-style.css" />
    <script src="<%=request.getContextPath()%>/scripts/clearFilter.js" defer></script>
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
        <table class="table">
            <thead>
            <tr>
                <th>Match ID</th>
                <th>Player 1</th>
                <th>Player 2</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                </tr>
                <tr>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="pagination-container">
        <button class="button">Prev</button>
        <div id="number-of-page-container"><p>1</p></div>
        <button class="button">Next</button>
    </div>
    <div class="link-container">
        <div class="link-subcontainer">
            <a class = "link" href="${pageContext.request.contextPath}/view/index.jsp">Back</a>
        </div>
    </div>
    </body>
</html>
