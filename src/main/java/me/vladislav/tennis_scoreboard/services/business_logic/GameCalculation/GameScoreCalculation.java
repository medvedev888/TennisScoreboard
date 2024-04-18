package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class GameScoreCalculation implements Calculation<GameResult> {

    @Override
    public GameResult calculate(CurrentMatch currentMatch) {

        Player currentPointWinner = currentMatch.getCurrentPointWinner();


        if (currentMatch.getMatchState() == MatchState.IN_PROCESS) {
                //first player is won the point
            if (currentPointWinner.equals(currentMatch.getPlayer1())) {
                currentMatch.setGameScoreOfPlayer1(currentMatch.getGameScoreOfPlayer1().next());

                // AD : AD
                if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE) {
                    currentMatch.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                    // AD + 1 : 40
                } else if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE_PLUS_ONE &&
                        currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT) {
                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                    return GameResult.PLAYER_1_WIN;
                    // AD : 0-30
                } else if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        (currentMatch.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                currentMatch.getGameScoreOfPlayer2() == GameScore.FIRST_POINT ||
                                currentMatch.getGameScoreOfPlayer2() == GameScore.SECOND_POINT)) {

                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_1_WIN;
                    // 40 : 0-15
                } else if (currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT &&
                        (currentMatch.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                currentMatch.getGameScoreOfPlayer2() == GameScore.FIRST_POINT)) {

                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_1_WIN;

                } else {
                    return GameResult.IN_PROCESS;
                }

                //second player is won the point
            } else if (currentPointWinner.equals(currentMatch.getPlayer2())) {
                currentMatch.setGameScoreOfPlayer2(currentMatch.getGameScoreOfPlayer2().next());

                // AD : AD
                if (currentMatch.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE) {
                    currentMatch.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                    // 40 : AD + 1
                } else if (currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE_PLUS_ONE &&
                        currentMatch.getGameScoreOfPlayer1() == GameScore.THIRD_POINT) {
                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);
                    return GameResult.PLAYER_2_WIN;
                    // 0-30 : AD
                } else if (currentMatch.getGameScoreOfPlayer2() == GameScore.ADVANTAGE &&
                        (currentMatch.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                currentMatch.getGameScoreOfPlayer1() == GameScore.FIRST_POINT ||
                                currentMatch.getGameScoreOfPlayer1() == GameScore.SECOND_POINT)) {

                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_2_WIN;
                    // 0-15 : 40
                } else if (currentMatch.getGameScoreOfPlayer2() == GameScore.THIRD_POINT &&
                        (currentMatch.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                currentMatch.getGameScoreOfPlayer1() == GameScore.FIRST_POINT)) {

                    currentMatch.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatch.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_2_WIN;

                } else {
                    return GameResult.IN_PROCESS;
                }

            } else {
                throw new RuntimeException("Current point winner is none");
            }
            return GameResult.IN_PROCESS;
        } else {
            throw new RuntimeException("Match is not in process");
        }
    }
}
