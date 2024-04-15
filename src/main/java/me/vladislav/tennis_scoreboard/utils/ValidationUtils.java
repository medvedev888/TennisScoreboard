package me.vladislav.tennis_scoreboard.utils;

public class ValidationUtils {

    public static boolean validateName(String name){
        if(name != null && !(name.isEmpty()) && name.matches("[a-zA-Zа-яА-Я ]+")){
            return true;
        }
        return false;
    }
}
