package task1;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Secant {
    public static double sec(double x) {
        double accuracy = 0.01;
        double computedCos = cos(x, accuracy);

        if (Math.abs(computedCos) < accuracy)
            return Double.POSITIVE_INFINITY;

        return 1 / cos(x, accuracy);
    }

    public static double cos(double x, double accuracy) {
        double previousResult = 0;
        double result = computeCosTaylorSeriesMember(x, 0);
        double currentAccuracy = 1;
        int i = 1;

        while (currentAccuracy > accuracy) {
            previousResult = result;
            result += computeCosTaylorSeriesMember(x, i);
            currentAccuracy = Math.abs(result - previousResult);
            i++;
        }

        return result;
    }

    public static double computeCosTaylorSeriesMember(double x, int n) {
        if (n < 0)
            throw new IllegalArgumentException("Argument n can't be negative: n = " + n);

        return Math.pow(-1, n) * Math.pow(x, 2 * n) / CombinatoricsUtils.factorial(2 * n);
    }
}
