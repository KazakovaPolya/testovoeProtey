package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "loginEmail") private WebElement emailInput;
    @FindBy(id = "loginPassword") private WebElement passwordInput;
    @FindBy(id = "authButton") private WebElement loginButton;
    @FindBy(id = "inputsPage") private WebElement inputPage;
    @FindBy(id = "emailFormatError") private WebElement emailFormatError;
    @FindBy(id = "invalidEmailPassword") private WebElement invalidEmailPasswordError;

    public LoginPage successlogin(){
        emailInput.sendKeys("test@protei.ru");
        passwordInput.sendKeys("test");
        loginButton.click();
        return this;
    }

    public LoginPage email(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage password(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage login() {
        loginButton.click();
        return this;
    }

    // Проверка успешного логина
    public boolean isLoginSuccessfull() {
        return inputPage.isDisplayed();
    }

    // Получение текста ошибки "Неверный E-Mail или пароль"
    public String getInvalidEmailOrPasswordError() {
        return invalidEmailPasswordError.getText();
    }

    // Получение ошибки формата Email
    public String getEmailFormatError() {
        return emailFormatError.getText();
    }
}

