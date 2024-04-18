package me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;

public class MatchScoreCalculation implements Calculation<MatchState> {
    @Override
    public MatchState calculate(CurrentMatch currentMatch) {
        Player currentSetWinner = currentMatch.getCurrentSetWinner();

        if(currentSetWinner.equals(currentMatch.getPlayer1())){
            currentMatch.setMatchScoreOfPlayer1(currentMatch.getMatchScoreOfPlayer1() + 1);

            if(currentMatch.getMatchScoreOfPlayer1() == 2){
                return MatchState.PLAYER_1_WON;
            }

            return MatchState.IN_PROCESS;
        } else if(currentSetWinner.equals(currentMatch.getPlayer2())){
            currentMatch.setMatchScoreOfPlayer2(currentMatch.getMatchScoreOfPlayer2() + 1);

            if(currentMatch.getMatchScoreOfPlayer2() == 2){
                return MatchState.PLAYER_2_WON;
            }

            return MatchState.IN_PROCESS;
        } else {
            return MatchState.IN_PROCESS;
        }
    }
}
