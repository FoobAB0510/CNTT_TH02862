package vn.fpoly;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AnnotationTest {
    private final Calculator calculator = new Calculator();
    @AfterTest
    public void testSubtract() {
        Assert.assertEquals(calculator.subtract(1, 2),-1);
        System.out.println(calculator.subtract(1, 2));
    }
    @BeforeTest
    public void testAdd() {
        Assert.assertEquals(calculator.add(1, 2),3);
        System.out.println(calculator.add(1, 2));
    }
    @Test
    public void testMultiply() {
        Assert.assertEquals(calculator.multiply(2, 3),6);
        System.out.println(calculator.multiply(2, 3));
    }
}
