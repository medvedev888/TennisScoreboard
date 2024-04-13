package me.vladislav.tennisscoreboard.services.business_logic.Calculation;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;

public interface Calculation {
    public void calculate(CurrentMatch currentMatch);
}
