package ru.netology.card.pageObject;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Data
public class NotificationsPageObject {
    private SelenideElement replanTitle;
    private SelenideElement successTitle;
    private SelenideElement successContent;

    public NotificationsPageObject() {
        replanTitle = $("[data-test-id=replan-notification] .notification__title");
        successTitle = $("[data-test-id=success-notification] .notification__title");
        successContent = $("[data-test-id=success-notification] .notification__content");
    }

    public void clickReplanButton() {
        $("[data-test-id=replan-notification]").$(byText("Перепланировать")).click();
    }
}
