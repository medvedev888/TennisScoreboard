package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dao.MatchDataAccessObject;
import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Match;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class FinishedMatchesPersistenceService {

    public void saveMatch(CurrentMatch currentMatch, MatchDataAccessObject matchDataAccessObject) {
        Player winner = currentMatch.getMatchState() == MatchState.PLAYER_1_WON ? currentMatch.getPlayer1() : currentMatch.getPlayer2();
        Match match = new Match(currentMatch.getPlayer1(), currentMatch.getPlayer2(), winner);
        matchDataAccessObject.add(match);
    }
}
