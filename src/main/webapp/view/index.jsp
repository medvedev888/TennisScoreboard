<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Tennis scoreboard</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/index-style.css" />
        <script src="<%=request.getContextPath()%>/scripts/linkContainerClickHandlerScript.js" defer></script>
    </head>
    <body>
        <h1 class="heading">Tennis scoreboard</h1>
        <div class="container">
            <div class="link-container">
                <a class = "link" href="${pageContext.request.contextPath}/new-match">New match</a>
            </div>
            <div class="link-container">
                <a class = "link" href="${pageContext.request.contextPath}/matches">Finished matches</a>
            </div>
        </div>
    </body>
</html>