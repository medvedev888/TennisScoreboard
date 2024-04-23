package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatchDTO;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.Calculation;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;

public class GameScoreCalculation implements Calculation<GameResult> {

    @Override
    public GameResult calculate(CurrentMatchDTO currentMatchDTO) {

        Player currentPointWinner = currentMatchDTO.getCurrentPointWinner();

        if (currentMatchDTO.getMatchState() == MatchState.IN_PROCESS) {
                //first player is won the point
            if (currentPointWinner.equals(currentMatchDTO.getPlayer1())) {
                currentMatchDTO.setGameScoreOfPlayer1(currentMatchDTO.getGameScoreOfPlayer1().next());

                // AD : AD
                if (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        currentMatchDTO.getGameScoreOfPlayer2() == GameScore.ADVANTAGE) {
                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                    // AD + 1 : 40
                } else if (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.ADVANTAGE_PLUS_ONE &&
                        currentMatchDTO.getGameScoreOfPlayer2() == GameScore.THIRD_POINT) {
                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);
                    return GameResult.PLAYER_1_WIN;
                    // AD : 0-30
                } else if (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        (currentMatchDTO.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer2() == GameScore.FIRST_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer2() == GameScore.SECOND_POINT)) {

                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_1_WIN;
                    // 40 : 0-15
                } else if (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.THIRD_POINT &&
                        (currentMatchDTO.getGameScoreOfPlayer2() == GameScore.START_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer2() == GameScore.FIRST_POINT)) {

                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_1_WIN;

                } else {
                    return GameResult.IN_PROCESS;
                }

                //second player is won the point
            } else if (currentPointWinner.equals(currentMatchDTO.getPlayer2())) {
                currentMatchDTO.setGameScoreOfPlayer2(currentMatchDTO.getGameScoreOfPlayer2().next());

                // AD : AD
                if (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.ADVANTAGE &&
                        currentMatchDTO.getGameScoreOfPlayer2() == GameScore.ADVANTAGE) {
                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

                    // 40 : AD + 1
                } else if (currentMatchDTO.getGameScoreOfPlayer2() == GameScore.ADVANTAGE_PLUS_ONE &&
                        currentMatchDTO.getGameScoreOfPlayer1() == GameScore.THIRD_POINT) {
                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);
                    return GameResult.PLAYER_2_WIN;
                    // 0-30 : AD
                } else if (currentMatchDTO.getGameScoreOfPlayer2() == GameScore.ADVANTAGE &&
                        (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer1() == GameScore.FIRST_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer1() == GameScore.SECOND_POINT)) {

                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);

                    return GameResult.PLAYER_2_WIN;
                    // 0-15 : 40
                } else if (currentMatchDTO.getGameScoreOfPlayer2() == GameScore.THIRD_POINT &&
                        (currentMatchDTO.getGameScoreOfPlayer1() == GameScore.START_POINT ||
                                currentMatchDTO.getGameScoreOfPlayer1() == GameScore.FIRST_POINT)) {

                    currentMatchDTO.setGameScoreOfPlayer1(GameScore.START_POINT);
                    currentMatchDTO.setGameScoreOfPlayer2(GameScore.START_POINT);

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
