import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest extends BaseTest {
    private final By emailInput = By.id("dataEmail");
    private final By nameInput = By.id("dataName");
    private final By genderSelect = By.id("dataGender");
    private final By genderMale = By.cssSelector("option:first-child");
    private final By genderFemale = By.cssSelector("option:last-child");
    private final By checkbox1 = By.id("dataCheck11");
    private final By checkbox2 = By.id("dataCheck12");
    private final By select1 = By.id("dataSelect21");
    private final By select2 = By.id("dataSelect22");
    private final By select3 = By.id("dataSelect23");
    private final By submit = By.id("dataSend");
    private final By modalDialog = By.cssSelector("div.uk-modal-content");
    private final By modalClose = By.cssSelector("button.uk-modal-close");
    private final By dataEmail = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(1)");
    private final By dataName = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(2)");
    private final By dataGender = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(3)");
    private final By dataSelect1 = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(4)");
    private final By dataSelect2 = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(5)");
    private final By validateMessage = By.cssSelector("div.uk-alert");

    // Метод для заполнения и отправки формы
    private void fillAndSubmitForm(String email, String name, String gender, String option, String radioValue) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(nameInput).sendKeys(name);
        Select genderDropdown = new Select(driver.findElement(genderSelect));
        genderDropdown.selectByVisibleText(gender);
        if (!option.isEmpty()){
        switch (option){
            case "1.1" : {
                driver.findElement(checkbox1).click();
                break;
            }
            case "1.2" : {
                driver.findElement(checkbox2).click();
                break;
            }
            case "1.1, 1.2" : {
                driver.findElement(checkbox1).click();
                driver.findElement(checkbox2).click();
                break;
            }
        }
        }
        if (!radioValue.isEmpty()) {
            switch (radioValue){
                case "2.1": {driver.findElement(select1).click(); break;}
                case "2.2": {driver.findElement(select2).click(); break;}
                case "2.3": {driver.findElement(select3).click(); break;}
            }
        }
        driver.findElement(submit).click();
    }

    private void checkDatesInTable(String email, String name, String gender, String select1, String select2){
        assertEquals(email, driver.findElement(dataEmail).getText());
        assertEquals(name, driver.findElement(dataName).getText());
        assertEquals(gender, driver.findElement(dataGender).getText());
        if (!select1.isEmpty())
            assertEquals(select1, driver.findElement(dataSelect1).getText());
        else
            assertEquals("Нет", driver.findElement(dataSelect1).getText());
        if (!select2.isEmpty())
            assertEquals(select2, driver.findElement(dataSelect2).getText());
        else
            assertEquals("",driver.findElement(dataSelect2).getText());
    }

    @ParameterizedTest()
    @CsvSource({
            "test@mail.ru, Roman, Мужской, 1.1, 2.1",
            "test@mail.ru, Natali, Женский, 1.1, 2.2",
            "ssffd@gmail.ru, Petr, Мужской, 1.1, 2.3",
            "dssdfsd@eee.com, Polina, Женский, 1.1, "
    })
    void testSuccessfulSubmission(String email, String name, String gender, String select1, String select2) {
        LoginTest loginTest = new LoginTest();
        loginTest.testSuccessfulLogin();
        fillAndSubmitForm(email, name, gender, select1, select2);
        assertEquals("Данные добавлены.", driver.findElement(modalDialog).getText());
        driver.findElement(modalClose).click();
        checkDatesInTable(email, name, gender, select1, select2);
    }

    @Test
    void testEmptyFormSubmission() {
        driver.findElement(submit).click();
        assertEquals("Неверный формат E-Mail", driver.findElement(validateMessage).getText());
    }

}
