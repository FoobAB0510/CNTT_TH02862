package vn.fpoly;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorDivisionTest {
    private Calculator calculator;
    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }
    @Test
    public void testDivision() {
        Assert.assertEquals(calculator.divide(6,3),2);
    }
    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        calculator.divide(10,0);
    }
    @AfterClass
    public void tearDown() {
        calculator = null;
    }
}
