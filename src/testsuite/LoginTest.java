package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseURL = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseURL);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        WebElement findUserName = driver.findElement(By.id("username"));
        findUserName.sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        // Click on ‘LOGIN’ button
        driver.findElement(By.tagName("i")).click();
        // Verify the text “Secure Area”
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals("verify text secure area", actualText, expectedText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Enter “tomsmith1” username
        WebElement findUserName = driver.findElement(By.id("username"));
        findUserName.sendKeys("tomsmith1");

        // Enter “SuperSecretPassword!” password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Click on ‘LOGIN’ button
        driver.findElement(By.tagName("i")).click();

        // Verify the error message “Your username is invalid!”
        String expectedDisplay = "Your username is invalid!\n"+"×";

        String actualDisplay=driver.findElement(By.id("flash")).getText();
        Assert.assertEquals("verify error message",expectedDisplay,actualDisplay);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        // Enter “tomsmith” username
        WebElement findUserName = driver.findElement(By.id("username"));
        findUserName.sendKeys("tomsmith");
        // Enter “SuperSecretPassword” password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        // Click on ‘LOGIN’ button
        driver.findElement(By.tagName("i")).click();
        // Verify the error message “Your password is invalid!”
        String expectedDisplay = "Your password is invalid!\n"+"×";
        String actualDisplay=driver.findElement(By.xpath("//div[@id = 'flash']")).getText();
        Assert.assertEquals("verify error messege",expectedDisplay,actualDisplay);
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}
