package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class GameScoreCalculation implements Calculation<GameResult> {

    @Override
    public GameResult calculate(CurrentMatch currentMatch) {

        Player currentPointWinner = currentMatch.getCurrentPointWinner();

        if(currentMatch.getMatchState() == MatchState.IN_PROCESS){

            // Deuce (40 : 40)
            if(currentMatch.getGameScoreOfPlayer1() == currentMatch.getGameScoreOfPlayer2() &&
                    currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT){

                // Player 1 won the point
                if(currentPointWinner.equals(currentMatch.getPlayer1())){
                    currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().nextInDeuceStage());
                    // Player 2 won the point
                } else if(currentPointWinner.equals(currentMatch.getPlayer2())){
                    currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().nextInDeuceStage());
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }

                return GameResult.IN_PROCESS;
                // Advantage (AD : AD)
            } else if(currentMatch.getGameScoreOfPlayer1() == currentMatch.getGameScoreOfPlayer2() &&
                    currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE) {

                currentMatch.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                currentMatch.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                // Player 1 won the point
                if (currentPointWinner.equals(currentMatch.getPlayer1())) {
                    currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().nextInDeuceStage());
                    // Player 2 won the point
                } else if (currentPointWinner.equals(currentMatch.getPlayer2())) {
                    currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().nextInDeuceStage());
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }
                return GameResult.IN_PROCESS;

                // check is deuce phase
            } else if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE || currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE) {
                // Player 1 won the point
                if (currentPointWinner.equals(currentMatch.getPlayer1())) {
                    // AD + 1 : 40
                    if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE && currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT) {
                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                        return GameResult.PLAYER_1_WIN;
                        // 40 + 1: AD
                    } else {
                        currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().nextInDeuceStage());
                        return GameResult.IN_PROCESS;
                    }
                    // Player 2 won the point
                } else if (currentPointWinner.equals(currentMatch.getPlayer2())) {
                    // AD : 40 + 1
                    if (currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE && currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT) {
                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                        return GameResult.PLAYER_2_WIN;
                        // 40 : AD + 1
                    } else {
                        currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().nextInDeuceStage());
                        return GameResult.IN_PROCESS;
                    }
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }
            } else {
                // Player 1 won the point
                if(currentPointWinner.equals(currentMatch.getPlayer1())){

                    if(currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT &&
                            (currentMatch.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                    currentMatch.getGameScoreOfPlayer2() == GameScore.FIRST_POINT ||
                                    currentMatch.getGameScoreOfPlayer2() == GameScore.SECOND_POINT)) {

                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                        return GameResult.PLAYER_1_WIN;

                    } else {
                        currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().next());
                        return GameResult.IN_PROCESS;
                    }
                    // Player 2 won the point
                } else if(currentPointWinner.equals(currentMatch.getPlayer2())){

                    if(currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT &&
                            (currentMatch.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                    currentMatch.getGameScoreOfPlayer1() == GameScore.FIRST_POINT ||
                                    currentMatch.getGameScoreOfPlayer1() == GameScore.SECOND_POINT)) {

                        currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                        currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                        return GameResult.PLAYER_2_WIN;

                    } else {
                        currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().next());
                        return GameResult.IN_PROCESS;
                    }
                } else {
                    throw new RuntimeException("Game score calculation failed" + currentPointWinner + " is null;");
                }
            }
        } else {
            throw new RuntimeException("Match is not in process");
        }
    }
}
