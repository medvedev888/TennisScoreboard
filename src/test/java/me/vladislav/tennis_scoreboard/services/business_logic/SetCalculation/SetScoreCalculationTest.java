package me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.models.Player;
import me.vladislav.tennis_scoreboard.services.business_logic.MatchCalculation.MatchState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SetScoreCalculationTest {
    // Test when the set score is 0:0 and the first player wins a point
    @Test
    public void testSetScoreCalculation0To0() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player1);

        //expected
        expected.setCurrentGameWinner(player1);
        expected.setSetScoreOfPlayer1(1);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    // Test when the set score is 4:5 and the second player wins a point
    @Test
    public void testSetScoreCalculation4To5() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player2);
        actual.setSetScoreOfPlayer1(4);
        actual.setSetScoreOfPlayer2(5);

        //expected
        expected.setCurrentGameWinner(player2);
        expected.setSetScoreOfPlayer1(0);
        expected.setSetScoreOfPlayer2(0);
        expected.setPreviousSet1ScoreOfPlayer1(4);
        expected.setPreviousSet1ScoreOfPlayer2(6);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        SetResult setResult = setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
        assertEquals(SetResult.PLAYER_2_WIN, setResult, "Match result should be equal");
    }

    // Test when the set score is 6:5 and the first player wins a point
    @Test
    public void testSetScoreCalculation6To5() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player1);
        actual.setSetScoreOfPlayer1(6);
        actual.setSetScoreOfPlayer2(5);

        //expected
        expected.setCurrentGameWinner(player1);
        expected.setSetScoreOfPlayer1(0);
        expected.setSetScoreOfPlayer2(0);
        expected.setPreviousSet1ScoreOfPlayer1(7);
        expected.setPreviousSet1ScoreOfPlayer2(5);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        SetResult setResult = setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
        assertEquals(SetResult.PLAYER_1_WIN, setResult, "Match result should be equal");
    }

    // Test when the set score is 5:5 and the second player wins a point
    @Test
    public void testSetScoreCalculation5To5() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player2);
        actual.setSetScoreOfPlayer1(5);
        actual.setSetScoreOfPlayer2(5);

        //expected
        expected.setCurrentGameWinner(player2);
        expected.setSetScoreOfPlayer1(5);
        expected.setSetScoreOfPlayer2(6);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    // Test when the set score is 6:5 and the first player wins a point
    @Test
    public void testSetScoreCalculation5To6() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player1);
        actual.setSetScoreOfPlayer1(5);
        actual.setSetScoreOfPlayer2(6);

        //expected
        expected.setCurrentGameWinner(player1);
        expected.setSetScoreOfPlayer1(6);
        expected.setSetScoreOfPlayer2(6);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }

    // Test when the set score is 13:14 and the second player wins a point
    @Test
    public void testSetScoreCalculation13To14() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player2);
        actual.setSetScoreOfPlayer1(13);
        actual.setSetScoreOfPlayer2(14);

        //expected
        expected.setCurrentGameWinner(player2);
        expected.setSetScoreOfPlayer1(0);
        expected.setSetScoreOfPlayer2(0);
        expected.setPreviousSet1ScoreOfPlayer1(13);
        expected.setPreviousSet1ScoreOfPlayer2(15);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        SetResult setResult = setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
        assertEquals(SetResult.PLAYER_2_WIN, setResult, "Match result should be equal");
    }

    // Test when the set score is 29:30 and the first player wins a point
    @Test
    public void testSetScoreCalculation29To30() {
        Player player1 = new Player(1, "Vlad");
        Player player2 = new Player(2, "Maks");
        UUID uuid = UUID.randomUUID();
        CurrentMatch actual = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);
        CurrentMatch expected = new CurrentMatch(uuid, player1, player2, MatchState.IN_PROCESS);

        //actual
        actual.setCurrentGameWinner(player1);
        actual.setSetScoreOfPlayer1(29);
        actual.setSetScoreOfPlayer2(30);

        //expected
        expected.setCurrentGameWinner(player1);
        expected.setSetScoreOfPlayer1(30);
        expected.setSetScoreOfPlayer2(30);

        SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

        setScoreCalculation.calculate(actual);

        assertEquals(expected, actual, "Matches should be equal");
    }
}
