package ru.netology.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String successText;
    String replanText;

    public CardDeliveryTest() {
        successText = "Успешно!";
        replanText = "Необходимо подтверждение";
    }
    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        RecipientData recipient = new RecipientData();
        FormPageObject form = new FormPageObject();
        NotificationsPageObject notifications = new NotificationsPageObject();
        // initial form filling
        form.enterCity(recipient.getCity());
        form.enterDate(recipient.getDate());
        form.enterName(recipient.getName());
        form.enterPhone(recipient.getPhone());
        form.acceptAgreement();
        form.submitForm();
        // 1st submit check success
        notifications.getSuccessTitle().shouldHave(text(successText));
        notifications.getSuccessContent().shouldHave(text(recipient.getDate()));
        // increase date as 10 days more from current
        form.enterDate(recipient.getDate(10));
        form.submitForm();
        // check and submit replan
        notifications.getReplanTitle().shouldHave(text(replanText));
        notifications.clickReplanButton();
        // check success
        notifications.getSuccessTitle().shouldHave(text(successText));
        notifications.getSuccessContent().shouldHave(text(recipient.getDate()));
    }
}
