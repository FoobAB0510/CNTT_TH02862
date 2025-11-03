package vn.fpoly;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
    WebDriver driver;
    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else {throw new RuntimeException("Unsupported browser: " + browser);}
        driver.manage().window().maximize();
    }
    @Test
    @Parameters({"url","expectedTitle"})
    public void testTitle(String url, String expectedTitle) {
        driver.get(url);
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }
    @Test
    @Parameters({"username","password"})
    public void testLogin(String username, String password) {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("submit"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        boolean successMessage = driver.getPageSource().contains("Logged In Successfully");
        Assert.assertTrue(successMessage);
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
