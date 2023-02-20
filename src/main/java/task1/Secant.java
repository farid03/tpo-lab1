package task1;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Secant {
    public static double sec(final double x) {
        final double accuracy = 1E-3;
        final double computedCos = cos(x, accuracy);

        if (Math.abs(computedCos) < accuracy) {
            return Double.POSITIVE_INFINITY;
        }

        return 1 / cos(x, accuracy);
    }

    private static double cos(final double bigX, final double accuracy) {
        final double x = bigX % (2 * Math.PI);
        double result = computeCosTaylorSeriesMember(x, 0);
        double currentAccuracy = computeCurrentAccuracy(x, 1);
        int i = 1;

        while (currentAccuracy > accuracy) {
            result += computeCosTaylorSeriesMember(x, i);
            currentAccuracy = computeCurrentAccuracy(x, i);
            i++;
        }

        return result;
    }

    private static double computeCurrentAccuracy(final double x, final int n) {
        return Math.pow(x, 2 * n) / CombinatoricsUtils.factorial(2 * n);
    }

    private static double computeCosTaylorSeriesMember(final double x, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Argument n can't be negative: n = " + n);
        }

        return Math.pow(-1, n) * Math.pow(x, 2 * n) / CombinatoricsUtils.factorial(2 * n);
    }
}
