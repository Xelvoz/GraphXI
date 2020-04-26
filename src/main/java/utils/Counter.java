package utils;

public class Counter {
    private static int COUNTER;

    private Counter() {
    }

    public static int getID() {
        return COUNTER++;
    }
}
