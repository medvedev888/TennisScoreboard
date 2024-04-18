package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import lombok.Getter;

public enum GameScore {
    START_POINT("0"),
    FIRST_POINT("15"),
    SECOND_POINT("30"),
    THIRD_POINT("40"),
    ADVANTAGE("AD"),
    ADVANTAGE_PLUS_ONE("AD+1");

    public GameScore next(){
        if(this == GameScore.ADVANTAGE_PLUS_ONE){
            return GameScore.START_POINT;
        } else {
            return values()[this.ordinal() + 1];
        }
    }

    @Getter
    private final String pointValue;

    GameScore(String pointValue) {
        this.pointValue = pointValue;
    }

}
