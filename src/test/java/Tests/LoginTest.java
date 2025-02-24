package Tests;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    //Успешный вход с корректными данными
    @Test
    void testSuccessfulLogin() {
        loginPage.successlogin();
    }

    // Попытка входа с неверным паролем
    @Test
    void testInvalidPassword() {
        loginPage.invalidPassword();
    }

    // Попытка входа с несуществующим email
    @Test
    void testInvalidEmail() {
        loginPage.invalidEmail();
    }

    // Тест 4: Попытка входа с пустыми полями
    @Test
    void testEmptyFields() {
        loginPage.emptyFields();
    }

    // Попытка входа с некорректным email
    @Test
    void testInvalidEmailFormat() {
        loginPage.invalidEmailFormat();
    }
}