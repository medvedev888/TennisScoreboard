package me.vladislav.tennisscoreboard.services.business_logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Score {
    private int player1Score;
    private int player2Score;
}
