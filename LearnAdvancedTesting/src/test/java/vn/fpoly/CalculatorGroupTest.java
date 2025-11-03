package vn.fpoly;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorGroupTest {
    private Calculator calculator;
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calculator = new Calculator();
    }
    @Test(groups = "math")
    public void testAdd() {
        Assert.assertEquals(calculator.add(1, 2), 3);
    }
    @Test(groups = "math")
    public void testSubtract() {
        Assert.assertEquals(calculator.subtract(1, 2), -1);
    }
    @Test(groups = "math")
    public void testMultiply() {
        Assert.assertEquals(calculator.multiply(2, 2), 4);
    }
    @Test(groups = "division")
    public void testDivide() {
        Assert.assertEquals(calculator.divide(2, 2), 1);
    }
    @Test(groups = "division", expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }
    @AfterClass
    public void tearDown() {
        calculator = null;
    }
}
