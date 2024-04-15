package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameScoreCalculationTest {

    // started score is 0 : 0
    // first player is win point
    @Test
    void testGameScoreCalculation0To0(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentPointWinner(player1);

        //expected
        expected.setCurrentPointWinner(player1);
        expected.setGameScoreOfPlayer1(GameScore.FIRST_POINT);

        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        gameScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    // started score is 0 : 40
    // second player is win point
    @Test
    void testGameScoreCalculation0To40(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentPointWinner(player2);
        actual.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

        //expected
        expected.setCurrentPointWinner(player2);
        expected.setGameScoreOfPlayer1(GameScore.START_POINT);
        expected.setGameScoreOfPlayer2(GameScore.START_POINT);


        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        GameResult result = gameScoreCalculation.calculate(actual);

        assertEquals(GameResult.PLAYER_2_WIN, result, "Result is not equals");
        assertEquals(expected, actual, "Matches should be equal");
    }

    //started score is 40 : 40
    // first player is win point
    @Test
    void testGameScoreCalculation40To40(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentPointWinner(player1);
        actual.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
        actual.setGameScoreOfPlayer2(GameScore.THIRD_POINT);


        //expected
        expected.setCurrentPointWinner(player1);
        expected.setGameScoreOfPlayer1(GameScore.ADVANTAGE);
        expected.setGameScoreOfPlayer2(GameScore.THIRD_POINT);

        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        gameScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    //started score is AD : AD
    // second player is win point
    @Test
    void testGameScoreCalculationADToAD(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentPointWinner(player2);
        actual.setGameScoreOfPlayer1(GameScore.ADVANTAGE);
        actual.setGameScoreOfPlayer2(GameScore.ADVANTAGE);


        //expected
        expected.setCurrentPointWinner(player2);
        expected.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
        expected.setGameScoreOfPlayer2(GameScore.ADVANTAGE);

        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        gameScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    // Test when the game score is AD:40 and the second player wins a point
    @Test
    void testGameScoreCalculationADTo40(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        //actual
        actual.setCurrentPointWinner(player2);
        actual.setGameScoreOfPlayer1(GameScore.ADVANTAGE);
        actual.setGameScoreOfPlayer2(GameScore.THIRD_POINT);


        //expected
        expected.setCurrentPointWinner(player2);
        expected.setGameScoreOfPlayer1(GameScore.ADVANTAGE);
        expected.setGameScoreOfPlayer2(GameScore.ADVANTAGE);

        GameResult result = gameScoreCalculation.calculate(actual);

        assertEquals(GameResult.IN_PROCESS, result, "Result is not equals");
        assertEquals(expected, actual, "Matches should be equal");
    }

    // Test when the game score is 40:AD and the second player wins a point
    @Test
    void testGameScoreCalculation40ToAD(){
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();

        //actual
        actual.setCurrentPointWinner(player2);
        actual.setGameScoreOfPlayer1(GameScore.THIRD_POINT);
        actual.setGameScoreOfPlayer2(GameScore.ADVANTAGE);


        //expected
        expected.setCurrentPointWinner(player2);
        expected.setGameScoreOfPlayer1(GameScore.START_POINT);
        expected.setGameScoreOfPlayer2(GameScore.START_POINT);

        GameResult result = gameScoreCalculation.calculate(actual);

        assertEquals(GameResult.PLAYER_2_WIN, result, "Result is not equals");
        assertEquals(expected, actual, "Matches should be equal");
    }


    // Test when the game score is 0:0 and a player wins the point randomly
    @Test
    void testRandomGameThenPlayerWins() {
        // Arrange
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch currentMatch = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();
        GameResult result = GameResult.IN_PROCESS;

        // Act: Random game until a winner is determined
        while (result == GameResult.IN_PROCESS) {
            currentMatch.setCurrentPointWinner(Math.random() < 0.5 ? player1 : player2);
            result = gameScoreCalculation.calculate(currentMatch);
        }

        System.out.println("GameScore is" + result);
        // Assert: Ensure either player 1 or player 2 wins
        assertTrue(result == GameResult.PLAYER_1_WIN || result == GameResult.PLAYER_2_WIN,
                "Either player 1 or player 2 should win the game");
    }




}
