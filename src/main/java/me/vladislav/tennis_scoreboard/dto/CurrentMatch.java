package me.vladislav.tennis_scoreboard.dto;

import lombok.*;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameScore;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CurrentMatch {
    private UUID id;
    private Player player1;
    private Player player2;
    private Player currentPointWinner = null;
    private GameScore gameScoreOfPlayer1 = GameScore.START_POINT;
    private GameScore gameScoreOfPlayer2 = GameScore.START_POINT;
    private int setScoreOfPlayer1 = 0;
    private int setScoreOfPlayer2 = 0;
    private MatchState matchState = MatchState.IN_PROCESS;

    public CurrentMatch(UUID id, Player player1, Player player2, MatchState matchState) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.matchState = matchState;
    }

}