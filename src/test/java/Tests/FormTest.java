package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest extends BaseTest {

    //проверка различных комбинаций ввода полей, используется техника ТД попарного тестирования
    @ParameterizedTest()
    @CsvSource({
            "test@mail.ru, Roman, Мужской, 1.1, 2.1",
            "test@mail.ru, Natali, Женский, 1.1, 2.2",
            "ssffd@gmail.ru, Petr, Мужской, 1.1, 2.3",
            "dssdfsd@eee.com, Polina, Женский, 1.1, null",
            "fdffd@msd.ru, Pavel, Мужской, 1.2, 2.2",
            "fddfdfg@mail.ru, Irina, Женский, 1.2, 2.3",
            "fdf@mail.ru, Nikita, Мужской, 1.2, null",
            "dsd@mail.ru, Larisa, Женский, 1.2, 2.1",
            "fefe@mailru, Emil, Мужской, '1.1, 1.2', 2.3",
            "fd@mail.ru, Полина, Женский, '1.1, 1.2', null",
            "d@mail.ru, Kirill, Мужской, '1.1, 1.2', 2.1",
            "dg@mail.ru, Karina, Женский, '1.1, 1.2', 2.2",
            "fgdgd@gmail.com, Pashka, Мужской, null, null",
            "gfg@mail.com, Полина, Женский, null, 2.1",
            "gfffg@mail.com, 123456, Мужской, null, 2.2",
            "fd@mail.ru, П, Женский, null, 2.3"
    })
    void testSuccessfulSubmission(String email, String name, String gender, String select1, String select2) {
        loginPage.successlogin();
        formPage.fillAndSubmitForm(email, name, gender, select1, select2);
        driver.findElement(formPage.modalClose).click();
        formPage.checkDatesInTable(email, name, gender, select1, select2);
    }

    //отправка пустой формы
    @Test
    void testEmptyFormSubmission() {
        loginPage.successlogin();
        driver.findElement(formPage.submit).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(formPage.validateMessage).getText());
    }

    //Проверка валидации: некорректный E-Mail
    @Test
    void testInvalidEmail() {
        loginPage.successlogin();
        formPage.fillAndSubmitForm("invalid-email", "Иван", "Мужской", "null", "null");
        assertEquals("Неверный формат E-Mail", driver.findElement(formPage.validateMessage).getText());
    }

    //"Проверка валидации: пустое имя"
    @Test
    void testEmptyName() {
        loginPage.successlogin();
        formPage.fillAndSubmitForm("fd@mail.ru", "", "Мужской", "null", "null");
        assertEquals("Поле имя не может быть пустым", driver.findElement(formPage.validateMessage).getText());
    }

    //"Проверка добавления одинаковых записей"
    @Test
    void testDoubleEmail() {
        loginPage.successlogin();
        formPage.fillAndSubmitForm("fd@mail.ru", "Petr", "Мужской", "null", "null");
        driver.findElement(formPage.modalClose).click();
        formPage.checkDatesInTable("fd@mail.ru", "Petr", "Мужской", "null", "null");
        driver.findElement(formPage.submit).click();
        driver.findElement(formPage.modalClose).click();
        assertEquals(2,driver.findElements(formPage.table).size());
        formPage.checkDatesInTable("fd@mail.ru", "Petr", "Мужской", "null", "null");
    }

}
