package me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;

public class MatchScoreCalculation implements Calculation<MatchState> {
    @Override
    public MatchState calculate(CurrentMatchDTO currentMatchDTO) {
        Player currentSetWinner = currentMatchDTO.getCurrentSetWinner();

        if(currentSetWinner.equals(currentMatchDTO.getPlayer1())){
            currentMatchDTO.setMatchScoreOfPlayer1(currentMatchDTO.getMatchScoreOfPlayer1() + 1);

            if(currentMatchDTO.getMatchScoreOfPlayer1() == 2){
                return MatchState.PLAYER_1_WON;
            }

            return MatchState.IN_PROCESS;
        } else if(currentSetWinner.equals(currentMatchDTO.getPlayer2())){
            currentMatchDTO.setMatchScoreOfPlayer2(currentMatchDTO.getMatchScoreOfPlayer2() + 1);

            if(currentMatchDTO.getMatchScoreOfPlayer2() == 2){
                return MatchState.PLAYER_2_WON;
            }

            return MatchState.IN_PROCESS;
        } else {
            return MatchState.IN_PROCESS;
        }
    }
}
