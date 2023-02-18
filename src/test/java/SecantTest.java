import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task1.Secant;

class MathExtension {
    public static double sec(double x) {
        if (Math.abs(Math.cos(x)) < 1E-5)
            return Double.POSITIVE_INFINITY;

        return 1 / Math.cos(x);
    }
}

class SecantTest {
    double eps = 0.1;

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI})
    public void secantBasicCheck(double value) {
        Assertions.assertEquals(MathExtension.sec(value), Secant.sec(value), eps);
    }

}