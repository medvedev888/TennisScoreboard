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
public class CurrentMatchDTO {
    private UUID id;
    private Player player1;
    private Player player2;
    private Player currentPointWinner = null;
    private Player currentGameWinner = null;
    private Player currentSetWinner = null;

    private GameScore gameScoreOfPlayer1 = GameScore.START_POINT;
    private GameScore gameScoreOfPlayer2 = GameScore.START_POINT;
    private int setScoreOfPlayer1 = 0;
    private int setScoreOfPlayer2 = 0;
    private int matchScoreOfPlayer1 = 0;
    private int matchScoreOfPlayer2 = 0;
    private int previousSet1ScoreOfPlayer1 = 0;
    private int previousSet1ScoreOfPlayer2 = 0;
    private int previousSet2ScoreOfPlayer1 = 0;
    private int previousSet2ScoreOfPlayer2 = 0;

    private MatchState matchState = MatchState.IN_PROCESS;

    public CurrentMatchDTO(UUID id, Player player1, Player player2, MatchState matchState) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.matchState = matchState;
    }

}
