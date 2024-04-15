package me.vladislav.tennis_scoreboard.services;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;
import me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation.GameScoreCalculation;
//import me.vladislav.tennis_scoreboard.services.business_logic.SetCalculation.SetScoreCalculation;

public class MatchScoreCalculationService {
    private final GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();
//    private final SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

    public void calculationNewScoreForPlayer1(CurrentMatch currentMatch){
        gameScoreCalculation.calculate(currentMatch);
        //установить поле в currentMatch (кто выиграл текущий матч) - НЕ ТОЧНО
    }
}
