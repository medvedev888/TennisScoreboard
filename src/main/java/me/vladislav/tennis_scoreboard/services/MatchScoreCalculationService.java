package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameResult;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameScoreCalculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchScoreCalculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;
import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetResult;
import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetScoreCalculation;

public class MatchScoreCalculationService {
    private final GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();
    private final SetScoreCalculation setScoreCalculation = new SetScoreCalculation();
    private final MatchScoreCalculation matchScoreCalculation = new MatchScoreCalculation();
    private GameResult gameResult = GameResult.IN_PROCESS;
    private SetResult setResult = SetResult.IN_PROCESS;

    public void calculation(CurrentMatch currentMatch){

        if(gameResult == GameResult.IN_PROCESS) {
             gameResult = gameScoreCalculation.calculate(currentMatch);
        }

        if(gameResult == GameResult.PLAYER_1_WIN){
            currentMatch.setCurrentGameWinner(currentMatch.getPlayer1());
            setResult = setScoreCalculation.calculate(currentMatch);
            gameResult = GameResult.IN_PROCESS;
        } else if(gameResult == GameResult.PLAYER_2_WIN){
            currentMatch.setCurrentGameWinner(currentMatch.getPlayer2());
            setResult = setScoreCalculation.calculate(currentMatch);
            gameResult = GameResult.IN_PROCESS;
        }

        if(setResult == SetResult.PLAYER_1_WIN){
            currentMatch.setCurrentSetWinner(currentMatch.getPlayer1());
            currentMatch.setMatchState(matchScoreCalculation.calculate(currentMatch));
            setResult = SetResult.IN_PROCESS;
        } else if(setResult == SetResult.PLAYER_2_WIN){
            currentMatch.setCurrentSetWinner(currentMatch.getPlayer2());
            currentMatch.setMatchState(matchScoreCalculation.calculate(currentMatch));
            setResult = SetResult.IN_PROCESS;
        }

        if(currentMatch.getMatchState() == MatchState.PLAYER_1_WON){
            gameResult = GameResult.MATCH_IS_OVER;
            setResult = SetResult.MATCH_IS_OVER;
        } else if(currentMatch.getMatchState() == MatchState.PLAYER_2_WON){
            gameResult = GameResult.MATCH_IS_OVER;
            setResult = SetResult.MATCH_IS_OVER;
        }

        currentMatch.setCurrentPointWinner(null);
        currentMatch.setCurrentGameWinner(null);
        currentMatch.setCurrentSetWinner(null);
    }
}
