package me.vladislav.tennisscoreboard.services.business_logic;

import me.vladislav.tennisscoreboard.dto.CurrentMatch;

public interface Calculation<T> {
    public T calculate(CurrentMatch currentMatch);
}
