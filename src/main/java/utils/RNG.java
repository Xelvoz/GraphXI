package utils;

import java.util.Random;

public class RNG {
    private static Random random = new Random();

    private RNG() {
    }

    public static double real(double lowerbound, double upperbound) {
        return (upperbound - lowerbound) * random.nextDouble() + lowerbound;
    }

    public static int integer(int lowerbound, int upperbound) {
        return random.nextInt((upperbound - lowerbound + 1)) + lowerbound;
    }
}
