package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

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
        formPage
                .enterEmail(email)
                .enterName(name)
                .selectGender(gender)
                .selectCheckbox(select1)
                .selectRadio(select2)
                .submitForm()
                .closeModal();
        List<String> actualRow = formPage.getLastRowData();
        assertEquals(Arrays.asList(email, name, gender, !select1.equals("null") ? select1 : "Нет", !select2.equals("null") ? select2 : ""), actualRow);
    }

    //отправка пустой формы
    @Test
    void testEmptyFormSubmission() {
        loginPage.successlogin();
        formPage.submitForm();
        assertEquals("Неверный формат E-Mail", formPage.getValidationMessage());
    }

    @Test
    void testInvalidEmail() {
        loginPage.successlogin();
        formPage.enterEmail("invalid-email")
                .enterName("Иван")
                .selectGender("Мужской")
                .submitForm();
        assertEquals("Неверный формат E-Mail", formPage.getValidationMessage());
    }

    @Test
    void testEmptyName() {
        loginPage.successlogin();
        formPage.enterEmail("fd@mail.ru")
                .enterName("")
                .selectGender("Мужской")
                .submitForm();
        assertEquals("Поле имя не может быть пустым", formPage.getValidationMessage());
    }

    @Test
    void testDoubleEmail() {
        loginPage.successlogin();
        formPage.enterEmail("fd@mail.ru")
                .enterName("Petr")
                .selectGender("Мужской")
                .submitForm()
                .closeModal();
        List<String> firstEntry = formPage.getLastRowData();
        assertEquals(Arrays.asList("fd@mail.ru", "Petr", "Мужской", "Нет", ""), firstEntry);
        formPage.submitForm().closeModal();
        List<WebElement> rows = driver.findElements(By.cssSelector("#dataTable > tbody > tr"));
        assertEquals(2, rows.size());
    }
}
