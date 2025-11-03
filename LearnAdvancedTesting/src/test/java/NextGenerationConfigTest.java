import org.testng.Assert;
import org.testng.annotations.Test;
import vn.fpoly.Calculator;

public class NextGenerationConfigTest {
    Calculator calc = new Calculator();
    @Test
    public void nextGeneration() {
        Assert.assertEquals(calc.add(2,3), 5);
    }
}
