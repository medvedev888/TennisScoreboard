package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameResult;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameScoreCalculation;
import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetResult;
import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetScoreCalculation;

public class MatchScoreCalculationService {
    private final GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();
    private final SetScoreCalculation setScoreCalculation = new SetScoreCalculation();
    private GameResult gameResult = GameResult.IN_PROCESS;
    private SetResult setResult = SetResult.IN_PROCESS;

    public void calculation(CurrentMatch currentMatch){

        if(gameResult == GameResult.IN_PROCESS) {
             gameResult = gameScoreCalculation.calculate(currentMatch);
        }
        if(gameResult == GameResult.PLAYER_1_WIN){
            currentMatch.setCurrentGameWinner(currentMatch.getPlayer1());
            setResult = setScoreCalculation.calculate(currentMatch);
        } else if(gameResult == GameResult.PLAYER_2_WIN){
            currentMatch.setCurrentGameWinner(currentMatch.getPlayer2());
            setResult = setScoreCalculation.calculate(currentMatch);
        }
        OngoingMatchesService.getInstance().update(currentMatch.getId(), currentMatch);
    }
}
