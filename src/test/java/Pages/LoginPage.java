package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
    }
    public final By email = By.id("loginEmail");
    public final By password = By.id("loginPassword");
    public final By loginButton = By.id("authButton");
    public final By inputPage = By.id("inputsPage");
    public final By validateEmail = By.id("emailFormatError");
    public final By validateEmailPassword = By.id("invalidEmailPassword");

    public LoginPage successlogin(){
        driver.findElement(email).sendKeys("test@protei.ru");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        //проверяем, что появилась новая страница с формой
        assertEquals(true, driver.findElement(inputPage).isDisplayed());
        return this;
    }

    public LoginPage invalidPassword(){
        driver.findElement(email).sendKeys("test@protei.ru");
        driver.findElement(password).sendKeys("test11");
        driver.findElement(loginButton).click();
        // Проверяем сообщение об ошибке
        assertEquals("Неверный E-Mail или пароль", driver.findElement(validateEmailPassword).getText());
        // Проверяем, что остались на странице логина
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
        return this;
    }

    public LoginPage invalidEmail(){
        driver.findElement(email).sendKeys("testtt@protei.ru");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        // Проверяем сообщение об ошибке
        assertEquals("Неверный E-Mail или пароль", driver.findElement(validateEmailPassword).getText());
        // Проверяем, что остались на странице логина
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
        return this;
    }

    public LoginPage emptyFields(){
        driver.findElement(loginButton).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(validateEmail).getText());
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
        return this;
    }

    public LoginPage invalidEmailFormat(){
        driver.findElement(email).sendKeys("testtt");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(validateEmail).getText());
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
        return this;
    }
}
