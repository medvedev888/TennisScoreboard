<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New match</title>
    <link rel="stylesheet" href="styles/new-match-style.css" />
    <script src="scripts/placeholderHandler.js" defer></script>

</head>
    <body>
        <h1 class="heading">Tennis scoreboard</h1>
        <div class="container">
            <form action="new-match" method="post">
                <label for="player-1" id="label-1">Player 1:</label>
                <input type="text" class="player" id="player-1" name="player-1" placeholder="Enter the name of player 1">
                <br/>
                <label for="player-2" id="label-2">Player 2:</label>
                <input type="text" class="player" id="player-2" name="player-2" placeholder="Enter the name of player 2">
                <br/>
                <input type="submit" value="Submit" class="submit-button">
            </form>
        </div>
    </body>
</html>
