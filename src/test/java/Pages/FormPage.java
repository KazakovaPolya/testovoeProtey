package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class FormPage extends BasePage{
    public FormPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "dataEmail") private WebElement emailInput;
    @FindBy(id = "dataName") private WebElement nameInput;
    @FindBy(id = "dataGender") private WebElement genderSelect;
    @FindBy(id = "dataCheck11") private WebElement checkbox1;
    @FindBy(id = "dataCheck12") private WebElement checkbox2;
    @FindBy(id = "dataSelect21") private WebElement select1;
    @FindBy(id = "dataSelect22") private WebElement select2;
    @FindBy(id = "dataSelect23") private WebElement select3;
    @FindBy(id = "dataSend") private WebElement submitButton;
    @FindBy(css = "button.uk-modal-close") private WebElement modalCloseButton;
    @FindBy(css = "div.uk-alert") private WebElement validationMessage;
    // Данные последней строки таблицы
    @FindBy(css = "#dataTable > tbody > tr:last-child > td:nth-child(1)") private WebElement lastRowEmail;
    @FindBy(css = "#dataTable > tbody > tr:last-child > td:nth-child(2)") private WebElement lastRowName;
    @FindBy(css = "#dataTable > tbody > tr:last-child > td:nth-child(3)") private WebElement lastRowGender;
    @FindBy(css = "#dataTable > tbody > tr:last-child > td:nth-child(4)") private WebElement lastRowSelect1;
    @FindBy(css = "#dataTable > tbody > tr:last-child > td:nth-child(5)") private WebElement lastRowSelect2;

    // Вспомогательный метод для ввода текста
    private void setText(WebElement element, String text) {
//        element.clear();
        element.sendKeys(text);
    }

    // Заполнение полей формы
    public FormPage enterEmail(String email) {
        setText(emailInput, email);
        return this;
    }

    public FormPage enterName(String name) {
        setText(nameInput, name);
        return this;
    }

    public FormPage selectGender(String gender) {
        new Select(genderSelect).selectByVisibleText(gender);
        return this;
    }

    public FormPage selectCheckbox(String option) {
        if (option != null) {
            if (option.contains("1.1")) checkbox1.click();
            if (option.contains("1.2")) checkbox2.click();
        }
        return this;
    }

    public FormPage selectRadio(String value) {
        if (value != null) {
            switch (value) {
                case "2.1": select1.click(); break;
                case "2.2": select2.click(); break;
                case "2.3": select3.click(); break;
            }
        }
        return this;
    }

    public FormPage submitForm() {
        submitButton.click();
        return this;
    }

    public FormPage closeModal() {
        modalCloseButton.click();
        return this;
    }

    public String getValidationMessage() {
        return validationMessage.getText();
    }

    public List<String> getLastRowData() {
        return Arrays.asList(
                lastRowEmail.getText(),
                lastRowName.getText(),
                lastRowGender.getText(),
                lastRowSelect1.getText(),
                lastRowSelect2.getText()
        );
    }
}
