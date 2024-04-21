package me.vladislav.tennis_scoreboard.services;

import lombok.Setter;
import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
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
    @Setter
    private GameResult gameResult = GameResult.IN_PROCESS;
    @Setter
    private SetResult setResult = SetResult.IN_PROCESS;

    public void calculation(CurrentMatchDTO currentMatchDTO){

        if(gameResult == GameResult.IN_PROCESS) {
             gameResult = gameScoreCalculation.calculate(currentMatchDTO);
        }

        if(gameResult == GameResult.PLAYER_1_WIN){
            currentMatchDTO.setCurrentGameWinner(currentMatchDTO.getPlayer1());
            setResult = setScoreCalculation.calculate(currentMatchDTO);
            gameResult = GameResult.IN_PROCESS;
        } else if(gameResult == GameResult.PLAYER_2_WIN){
            currentMatchDTO.setCurrentGameWinner(currentMatchDTO.getPlayer2());
            setResult = setScoreCalculation.calculate(currentMatchDTO);
            gameResult = GameResult.IN_PROCESS;
        }

        if(setResult == SetResult.PLAYER_1_WIN){
            currentMatchDTO.setCurrentSetWinner(currentMatchDTO.getPlayer1());
            currentMatchDTO.setMatchState(matchScoreCalculation.calculate(currentMatchDTO));
            setResult = SetResult.IN_PROCESS;
        } else if(setResult == SetResult.PLAYER_2_WIN){
            currentMatchDTO.setCurrentSetWinner(currentMatchDTO.getPlayer2());
            currentMatchDTO.setMatchState(matchScoreCalculation.calculate(currentMatchDTO));
            setResult = SetResult.IN_PROCESS;
        }

        if(currentMatchDTO.getMatchState() == MatchState.PLAYER_1_WON){
            gameResult = GameResult.MATCH_IS_OVER;
            setResult = SetResult.MATCH_IS_OVER;
        } else if(currentMatchDTO.getMatchState() == MatchState.PLAYER_2_WON){
            gameResult = GameResult.MATCH_IS_OVER;
            setResult = SetResult.MATCH_IS_OVER;
        }

        currentMatchDTO.setCurrentPointWinner(null);
        currentMatchDTO.setCurrentGameWinner(null);
        currentMatchDTO.setCurrentSetWinner(null);
    }
}
