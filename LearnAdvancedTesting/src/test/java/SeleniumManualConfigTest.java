import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumManualConfigTest {
    private WebDriver driver;
    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
    }
    @Test
    public void testTitle() {
        driver.get("http://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
