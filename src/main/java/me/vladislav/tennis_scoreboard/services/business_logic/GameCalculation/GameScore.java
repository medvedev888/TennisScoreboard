package me.vladislav.tennis_scoreboard.services.business_logic.GameCalculation;

import lombok.Getter;

public enum GameScore {
    START_POINT("0"),
    FIRST_POINT("15"),
    SECOND_POINT("30"),
    THIRD_POINT("40"),
    ADVANTAGE("AD");

    public GameScore next(){
        if(this == GameScore.THIRD_POINT){
            return GameScore.START_POINT;
        } else {
            return values()[this.ordinal() + 1];
        }
    }

    public GameScore nextInDeuceStage(){
        if(this == GameScore.ADVANTAGE){
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
