package me.vladislav.tennisscoreboard.services.business_logic;

import lombok.Getter;

public enum GameScore {
    START_POINT("0"),
    POINT("15"),
    SECOND_POINT("30"),
    THIRD_POINT("40");

    public GameScore next(){
        if(this == GameScore.THIRD_POINT){
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
