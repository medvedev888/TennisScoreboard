package me.vladislav.tennisscoreboard.services.business_logic.Calculation;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;
import me.vladislav.tennisscoreboard.models.Player;
import me.vladislav.tennisscoreboard.services.business_logic.GameScore;
import me.vladislav.tennisscoreboard.services.business_logic.State;

public class GameScoreCalculation implements Calculation {

    @Override
    public void calculate(CurrentMatch currentMatch) {
        Player currentPointWinner = currentMatch.getCurrentPointWinner();
        if(currentMatch.getState() == State.IN_PROCESS){
            if(currentMatch.getGameScoreOfPlayer1() == currentMatch.getGameScoreOfPlayer2() &&
                    currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT){
                currentMatch.setState(State.ADVANTAGE);
            }

            if(currentPointWinner.equals(currentMatch.getPlayer1())){
                if(currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT &&
                        (currentMatch.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                currentMatch.getGameScoreOfPlayer2() == GameScore.FIRST_POINT ||
                                currentMatch.getGameScoreOfPlayer2() == GameScore.SECOND_POINT)) {
                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                    // SetScoreCalculation calculation()
                } else {
                    currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().next());
                }
            } else if(currentPointWinner.equals(currentMatch.getPlayer2())){
                currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().next());
            } else {
                throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
            }
        } else if(currentMatch.getState() == State.ADVANTAGE) {
            // не забыть изменить State = State.IN_PROCESS
        }
    }
}
