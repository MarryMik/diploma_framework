package org.example.utils;

public class Utils {
    public static void sleep(long time){
        try{
            Thread.sleep(time);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
