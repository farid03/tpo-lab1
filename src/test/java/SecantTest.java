import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.Secant;

class MathExtension {
    public static double sec(double x) {
        if (Math.abs(Math.cos(x)) < 1E-3) {
            return Double.POSITIVE_INFINITY;
        }

        return 1 / Math.cos(x);
    }
}

class SecantTest {
    double eps = 0.1;

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, 3 * Math.PI / 2, 5 * Math.PI})
    @DisplayName("Check undefined result: x = Pi * (2n + 1) / 2")
    public void undefinedValuesCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - 0.1, Math.PI / 2 + 0.1})
    @DisplayName("Check close to undefined")
    public void closeToUndefinedCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0})
    @DisplayName("Check zero")
    public void zeroCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, 0.01})
    @DisplayName("Check close to zero")
    public void closeToZeroCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
    @DisplayName("Check basic trigonometric table values")
    public void secantBasicCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }
}