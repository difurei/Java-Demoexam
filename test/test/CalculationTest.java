package test;

import demoexam.testJar.Calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationTest {
    private final Calculation calculation = new Calculation();

    @Test
    @DisplayName("тест 1")
    public void test1()
    {
        Assertions.assertEquals(-1, calculation.getQuantity(4, 1, 1, 1, 1));
    }
}
