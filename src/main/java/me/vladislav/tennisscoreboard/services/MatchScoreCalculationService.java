package me.vladislav.tennisscoreboard.services;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;
import me.vladislav.tennisscoreboard.services.business_logic.Calculation.GameScoreCalculation;
import me.vladislav.tennisscoreboard.services.business_logic.Calculation.SetScoreCalculation;

public class MatchScoreCalculationService {
    private final GameScoreCalculation gameScoreCalculation = new GameScoreCalculation();
    private final SetScoreCalculation setScoreCalculation = new SetScoreCalculation();

    public void calculationNewScoreForPlayer1(CurrentMatch currentMatch){

        //установить поле в currentMatch (кто выиграл текущий матч) - НЕ ТОЧНО
    }
}
