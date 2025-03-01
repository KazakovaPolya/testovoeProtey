package Tests;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    //Успешный вход с корректными данными
    @Test
    void testSuccessfulLogin() {
        loginPage
                .email("test@protei.ru")
                .password("test")
                .login();
        Assertions.assertEquals(true, loginPage.isLoginSuccessfull());
    }

    // Попытка входа с неверным паролем
    @Test
    void testInvalidPassword() {
        loginPage
                .email("test@protei.ru")
                .password("test1")
                .login();
        Assertions.assertEquals("Неверный E-Mail или пароль", loginPage.getInvalidEmailOrPasswordError());
        Assertions.assertEquals(false, loginPage.isLoginSuccessfull());
    }

    // Попытка входа с несуществующим email
    @Test
    void testInvalidEmail() {
        loginPage
                .email("test@protei.ru")
                .password("test1")
                .login();
        Assertions.assertEquals("Неверный E-Mail или пароль", loginPage.getInvalidEmailOrPasswordError());
        Assertions.assertEquals(false, loginPage.isLoginSuccessfull());
    }

    // Тест 4: Попытка входа с пустыми полями
    @Test
    void testEmptyFields() {
        loginPage
                .login();
        Assertions.assertEquals("Неверный формат E-Mail",loginPage.getEmailFormatError());
        Assertions.assertEquals(false, loginPage.isLoginSuccessfull());
    }

    // Попытка входа с некорректным email
    @Test
    void testInvalidEmailFormat() {
        loginPage
                .email("testprotei.ru")
                .password("test1")
                .login();
        Assertions.assertEquals("Неверный формат E-Mail", loginPage.getEmailFormatError());
        Assertions.assertEquals(false, loginPage.isLoginSuccessfull());
    }
}