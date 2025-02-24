package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormPage extends BasePage{
    public FormPage(WebDriver driver){
        super(driver);
    }
    public final By emailInput = By.id("dataEmail");
    public final By nameInput = By.id("dataName");
    public final By genderSelect = By.id("dataGender");
    public final By checkbox1 = By.id("dataCheck11");
    public final By checkbox2 = By.id("dataCheck12");
    public final By select1 = By.id("dataSelect21");
    public final By select2 = By.id("dataSelect22");
    public final By select3 = By.id("dataSelect23");
    public final By submit = By.id("dataSend");
    public final By modalClose = By.cssSelector("button.uk-modal-close");
    public final By table = By.cssSelector("#dataTable > tbody > tr");
    public final By dataEmail = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(1)");
    public final By dataName = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(2)");
    public final By dataGender = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(3)");
    public final By dataSelect1 = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(4)");
    public final By dataSelect2 = By.cssSelector("#dataTable > tbody > tr:last-child > td:nth-child(5)");
    public final By validateMessage = By.cssSelector("div.uk-alert");

    // Метод для заполнения и отправки формы
    public void fillAndSubmitForm(String email, String name, String gender, String option, String radioValue) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(nameInput).sendKeys(name);
        Select genderDropdown = new Select(driver.findElement(genderSelect));
        genderDropdown.selectByVisibleText(gender);
        if (!option.equals("null")){
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
        if (!radioValue.equals("null")) {
            switch (radioValue){
                case "2.1": {driver.findElement(select1).click(); break;}
                case "2.2": {driver.findElement(select2).click(); break;}
                case "2.3": {driver.findElement(select3).click(); break;}
            }
        }
        driver.findElement(submit).click();
    }

    //метод для проверки данных в таблице
    public void checkDatesInTable(String email, String name, String gender, String select1, String select2){
        assertEquals(email, driver.findElement(dataEmail).getText());
        assertEquals(name, driver.findElement(dataName).getText());
        assertEquals(gender, driver.findElement(dataGender).getText());
        if (!select1.equals("null"))
            assertEquals(select1, driver.findElement(dataSelect1).getText());
        else
            assertEquals("Нет", driver.findElement(dataSelect1).getText());
        if (!select2.equals("null"))
            assertEquals(select2, driver.findElement(dataSelect2).getText());
        else
            assertEquals("",driver.findElement(dataSelect2).getText());
    }
}
