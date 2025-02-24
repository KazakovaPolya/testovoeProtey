package Tests;

import Pages.FormPage;
import Pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver = new ChromeDriver();;
    LoginPage loginPage = new LoginPage(driver);
    FormPage formPage = new FormPage(driver);

    @BeforeEach
    public void setUp() {
        driver.manage().window().maximize();
        driver.get("file:///Users/polina/Downloads/qa-test.html");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
