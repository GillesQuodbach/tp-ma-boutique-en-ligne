package fr.fms;

public class Math {
    public static int add(int num1, int num2){
        return num1 + num2;
    }

    public static int mul(int num1, int num2){
        return num1 * num2;
    }

    public static int sub(int num1, int num2){
        return num1 - num2;
    }

    public static int div(int num, int by){
        return num/by;
    }

    public static boolean pair(int num){
        return num%2==0;
    }
}
