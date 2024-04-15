package me.vladislav.tennis_scoreboard.services.business_logic;

import me.vladislav.tennis_scoreboard.dto.CurrentMatch;

public interface Calculation<T> {
    public T calculate(CurrentMatch currentMatch);
}
