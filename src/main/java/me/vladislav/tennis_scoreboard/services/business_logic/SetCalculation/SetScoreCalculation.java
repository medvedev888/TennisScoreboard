package me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class SetScoreCalculation implements Calculation<SetResult> {
    @Override
    public SetResult calculate(CurrentMatch currentMatch) {
        Player currentGameWinner = currentMatch.getCurrentGameWinner();
        if(currentMatch.getMatchState() == MatchState.IN_PROCESS){
            // first player won the game in game-phase
            if(currentGameWinner == currentMatch.getPlayer1()){
                currentMatch.setSetScoreOfPlayer1(currentMatch.getSetScoreOfPlayer1() + 1);

                if((currentMatch.getSetScoreOfPlayer1() == 6 && currentMatch.getSetScoreOfPlayer2() <= 4) ||
                        (currentMatch.getSetScoreOfPlayer1() == 7 && currentMatch.getSetScoreOfPlayer2() == 5)){

                    setPreviousSets(currentMatch);

                    currentMatch.setSetScoreOfPlayer1(0);
                    currentMatch.setSetScoreOfPlayer2(0);
                    return SetResult.PLAYER_1_WIN;

                } else if(currentMatch.getSetScoreOfPlayer1() >= 6 && currentMatch.getSetScoreOfPlayer2() >= 6){
                    if(currentMatch.getSetScoreOfPlayer1() - currentMatch.getSetScoreOfPlayer2() == 2){

                        setPreviousSets(currentMatch);

                        currentMatch.setSetScoreOfPlayer1(0);
                        currentMatch.setSetScoreOfPlayer2(0);
                        return SetResult.PLAYER_1_WIN;
                    }
                }
                // second player won the game in game-phase
            } else if(currentGameWinner == currentMatch.getPlayer2()){
                currentMatch.setSetScoreOfPlayer2(currentMatch.getSetScoreOfPlayer2() + 1);

                if((currentMatch.getSetScoreOfPlayer2() == 6 && currentMatch.getSetScoreOfPlayer1() <= 4) ||
                        (currentMatch.getSetScoreOfPlayer2() == 7 && currentMatch.getSetScoreOfPlayer1() == 5)){

                    setPreviousSets(currentMatch);

                    currentMatch.setSetScoreOfPlayer1(0);
                    currentMatch.setSetScoreOfPlayer2(0);
                    return SetResult.PLAYER_1_WIN;
                } else if(currentMatch.getSetScoreOfPlayer2() >= 6 && currentMatch.getSetScoreOfPlayer1() >= 6){
                    if(currentMatch.getSetScoreOfPlayer2() - currentMatch.getSetScoreOfPlayer1() == 2){

                        setPreviousSets(currentMatch);

                        currentMatch.setSetScoreOfPlayer1(0);
                        currentMatch.setSetScoreOfPlayer2(0);
                        return SetResult.PLAYER_1_WIN;
                    }
                }
            }
            return SetResult.IN_PROCESS;
        } else {
            throw new RuntimeException("Match is not in process");
        }
    }

    private void setPreviousSets(CurrentMatch currentMatch){
        // filling out the fields previous sets
        if(currentMatch.getPreviousSet1ScoreOfPlayer1() == 0 && currentMatch.getPreviousSet2ScoreOfPlayer1() == 0){
            currentMatch.setPreviousSet1ScoreOfPlayer1(currentMatch.getSetScoreOfPlayer1());
            currentMatch.setPreviousSet1ScoreOfPlayer2(currentMatch.getSetScoreOfPlayer2());
        } else {
            currentMatch.setPreviousSet2ScoreOfPlayer1(currentMatch.getSetScoreOfPlayer1());
            currentMatch.setPreviousSet2ScoreOfPlayer2(currentMatch.getSetScoreOfPlayer2());
        }
    }
}
