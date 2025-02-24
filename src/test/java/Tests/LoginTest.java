import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    public final By email = By.id("loginEmail");
    public final By password = By.id("loginPassword");
    public final By loginButton = By.id("authButton");
    public final By inputPage = By.id("inputsPage");
    public final By validateEmail = By.id("emailFormatError");
    public final By validateEmailPassword = By.id("invalidEmailPassword");

    //Успешный вход с корректными данными
    @Test
    void testSuccessfulLogin() {
        driver.findElement(email).sendKeys("test@protei.ru");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        //проверяем, что появилась новая страница с формой
        assertEquals(true, driver.findElement(inputPage).isDisplayed());
    }

    // Попытка входа с неверным паролем
    @Test
    void testInvalidPassword() {
        driver.findElement(email).sendKeys("test@protei.ru");
        driver.findElement(password).sendKeys("test11");
        driver.findElement(loginButton).click();
        // Проверяем сообщение об ошибке
        assertEquals("Неверный E-Mail или пароль", driver.findElement(validateEmailPassword).getText());
        // Проверяем, что остались на странице логина
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
    }

    // Попытка входа с несуществующим email
    @Test
    void testInvalidEmail() {
        driver.findElement(email).sendKeys("testtt@protei.ru");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        // Проверяем сообщение об ошибке
        assertEquals("Неверный E-Mail или пароль", driver.findElement(validateEmailPassword).getText());
        // Проверяем, что остались на странице логина
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
    }

    // Тест 4: Попытка входа с пустыми полями
    @Test
    void testEmptyFields() {
        driver.findElement(loginButton).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(validateEmail).getText());
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
    }

    // Попытка входа с некорректным email
    @Test
    void testInvalidEmailFormat() {
        driver.findElement(email).sendKeys("testtt");
        driver.findElement(password).sendKeys("test");
        driver.findElement(loginButton).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(validateEmail).getText());
        assertEquals(false, driver.findElement(inputPage).isDisplayed());
    }
}