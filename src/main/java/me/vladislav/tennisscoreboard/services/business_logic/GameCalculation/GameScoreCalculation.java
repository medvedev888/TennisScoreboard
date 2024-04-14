package me.vladislav.tennisscoreboard.services.business_logic.GameCalculation;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;
import me.vladislav.tennisscoreboard.models.Player;
import me.vladislav.tennisscoreboard.services.business_logic.Calculation;
import me.vladislav.tennisscoreboard.services.business_logic.MatchCalculation.MatchState;



public class GameScoreCalculation implements Calculation<GameResult> {

    @Override
    public GameResult calculate(CurrentMatch currentMatch) {

        Player currentPointWinner = currentMatch.getCurrentPointWinner();

        if(currentMatch.getMatchState() == MatchState.IN_PROCESS){
            //deuce (40 : 40)
            if(currentMatch.getGameScoreOfPlayer1() == currentMatch.getGameScoreOfPlayer2() &&
                    currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT){

                    // player 1 won the point
                if(currentPointWinner.equals(currentMatch.getPlayer1())){
                    currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().nextInDeuceStage());
                    // player 2 won the point
                } else if(currentPointWinner.equals(currentMatch.getPlayer2())){
                    currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().nextInDeuceStage());
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }

                return GameResult.IN_PROCESS;
                // AD : AD
            } else if(currentMatch.getGameScoreOfPlayer1() == currentMatch.getGameScoreOfPlayer2() &&
                    currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE){

                currentMatch.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                currentMatch.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                    // player 1 won the point
                if(currentPointWinner.equals(currentMatch.getPlayer1())){
                    currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().nextInDeuceStage());
                    // player 2 won the point
                } else if(currentPointWinner.equals(currentMatch.getPlayer2())){
                    currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().nextInDeuceStage());
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }
                return GameResult.IN_PROCESS;
                // AD + 1 : 40
            } else if(currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                    currentPointWinner.equals(currentMatch.getPlayer1()) && currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT) {

                currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                return GameResult.PLAYER_1_WINS;

                // 40 : AD + 1
            } else if(currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE &&
                    currentPointWinner == currentMatch.getPlayer2() && currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT) {

                currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                return GameResult.PLAYER_2_WINS;

            } else {
                //Player 1 is won point
                if(currentPointWinner.equals(currentMatch.getPlayer1())){

                    if(currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT &&
                            (currentMatch.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                    currentMatch.getGameScoreOfPlayer2() == GameScore.FIRST_POINT ||
                                    currentMatch.getGameScoreOfPlayer2() == GameScore.SECOND_POINT)) {

                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    } else {
                        currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().next());
                    }
                    return GameResult.IN_PROCESS;
                    //Player 2 is won point
                } else if(currentPointWinner.equals(currentMatch.getPlayer2())){

                    if(currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT &&
                            (currentMatch.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                    currentMatch.getGameScoreOfPlayer1() == GameScore.FIRST_POINT ||
                                    currentMatch.getGameScoreOfPlayer1() == GameScore.SECOND_POINT)) {

                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    } else {
                        currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().next());
                    }
                    return GameResult.IN_PROCESS;

                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }
            }
        } else {
            throw new RuntimeException("Match is not in process");
        }
    }
}
