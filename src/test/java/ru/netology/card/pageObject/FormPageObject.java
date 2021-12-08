package ru.netology.card.pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class FormPageObject {
    private SelenideElement form;

    public FormPageObject() {
        form = $("form");
    }

    public void enterCity(String value) {
        form.$("[data-test-id=city] input").setValue(value);
    }

    public void enterName(String value) {
        form.$("[data-test-id=name] input").setValue(value);
    }

    public void enterPhone(String value) {
        form.$("[data-test-id=phone] input").setValue(value);
    }

    public void enterDate(String value) {
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(value);
    }

    public void acceptAgreement() {
        form.$("[data-test-id=agreement]").click();
    }

    public void submitForm() {
        form.$(byText("Запланировать")).click();
    }
}
