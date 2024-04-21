package me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class SetScoreCalculation implements Calculation<SetResult> {
    @Override
    public SetResult calculate(CurrentMatchDTO currentMatchDTO) {

        Player currentGameWinner = currentMatchDTO.getCurrentGameWinner();

        if(currentMatchDTO.getMatchState() == MatchState.IN_PROCESS){
            // first player won the game in game-phase
            if(currentGameWinner.equals(currentMatchDTO.getPlayer1())){
                currentMatchDTO.setSetScoreOfPlayer1(currentMatchDTO.getSetScoreOfPlayer1() + 1);

                if((currentMatchDTO.getSetScoreOfPlayer1() == 6 && currentMatchDTO.getSetScoreOfPlayer2() <= 4) ||
                        (currentMatchDTO.getSetScoreOfPlayer1() == 7 && currentMatchDTO.getSetScoreOfPlayer2() == 5)){

                    setPreviousSets(currentMatchDTO);

                    currentMatchDTO.setSetScoreOfPlayer1(0);
                    currentMatchDTO.setSetScoreOfPlayer2(0);
                    return SetResult.PLAYER_1_WIN;

                } else if(currentMatchDTO.getSetScoreOfPlayer1() >= 6 && currentMatchDTO.getSetScoreOfPlayer2() >= 6){
                    if(currentMatchDTO.getSetScoreOfPlayer1() - currentMatchDTO.getSetScoreOfPlayer2() == 2){

                        setPreviousSets(currentMatchDTO);

                        currentMatchDTO.setSetScoreOfPlayer1(0);
                        currentMatchDTO.setSetScoreOfPlayer2(0);
                        return SetResult.PLAYER_1_WIN;
                    }
                }
                // second player won the game in game-phase
            } else if(currentGameWinner.equals(currentMatchDTO.getPlayer2())){
                currentMatchDTO.setSetScoreOfPlayer2(currentMatchDTO.getSetScoreOfPlayer2() + 1);

                if((currentMatchDTO.getSetScoreOfPlayer2() == 6 && currentMatchDTO.getSetScoreOfPlayer1() <= 4) ||
                        (currentMatchDTO.getSetScoreOfPlayer2() == 7 && currentMatchDTO.getSetScoreOfPlayer1() == 5)){

                    setPreviousSets(currentMatchDTO);

                    currentMatchDTO.setSetScoreOfPlayer1(0);
                    currentMatchDTO.setSetScoreOfPlayer2(0);
                    return SetResult.PLAYER_2_WIN;
                } else if(currentMatchDTO.getSetScoreOfPlayer2() >= 6 && currentMatchDTO.getSetScoreOfPlayer1() >= 6){
                    if(currentMatchDTO.getSetScoreOfPlayer2() - currentMatchDTO.getSetScoreOfPlayer1() == 2){

                        setPreviousSets(currentMatchDTO);

                        currentMatchDTO.setSetScoreOfPlayer1(0);
                        currentMatchDTO.setSetScoreOfPlayer2(0);
                        return SetResult.PLAYER_2_WIN;
                    }
                }
            }
            return SetResult.IN_PROCESS;
        } else {
            return SetResult.IN_PROCESS;
        }
    }

    private void setPreviousSets(CurrentMatchDTO currentMatchDTO){
        // filling out the fields previous sets
        if(currentMatchDTO.getPreviousSet1ScoreOfPlayer1() == 0 && currentMatchDTO.getPreviousSet1ScoreOfPlayer2() == 0){
            currentMatchDTO.setPreviousSet1ScoreOfPlayer1(currentMatchDTO.getSetScoreOfPlayer1());
            currentMatchDTO.setPreviousSet1ScoreOfPlayer2(currentMatchDTO.getSetScoreOfPlayer2());
        } else if(currentMatchDTO.getPreviousSet2ScoreOfPlayer1() == 0 && currentMatchDTO.getPreviousSet2ScoreOfPlayer2() == 0){
            currentMatchDTO.setPreviousSet2ScoreOfPlayer1(currentMatchDTO.getSetScoreOfPlayer1());
            currentMatchDTO.setPreviousSet2ScoreOfPlayer2(currentMatchDTO.getSetScoreOfPlayer2());
        }
    }
}
