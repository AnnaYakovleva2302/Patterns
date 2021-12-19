package ru.netology.card.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.card.pageObject.FormPageObject;
import ru.netology.card.pageObject.NotificationsPageObject;
import ru.netology.card.data.RecipientDataGenerator;
import ru.netology.card.data.UserInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String successText;
    String replanText;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    public CardDeliveryTest() {
        successText = "Успешно!";
        replanText = "Необходимо подтверждение";
    }

    @Test
    void shouldSubmitRequest() {
        UserInfo recipient = RecipientDataGenerator.Registration.generateUser("ru");
        int daysToAddForFirstMeeting = 4;
        String firstMeetingDate = RecipientDataGenerator.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        String secondMeetingDate = RecipientDataGenerator.generateDate(daysToAddForSecondMeeting);
        FormPageObject form = new FormPageObject();
        NotificationsPageObject notifications = new NotificationsPageObject();
        // initial form filling
        form.enterCity(recipient.getCity());
        form.enterDate(firstMeetingDate);
        form.enterName(recipient.getName());
        form.enterPhone(recipient.getPhone());
        form.acceptAgreement();
        form.submitForm();
        // 1st submit check success
        notifications.getSuccessTitle().shouldHave(text(successText), Duration.ofSeconds(3));
        notifications.getSuccessContent().shouldHave(text(firstMeetingDate));
        // increase date as 10 days more from current
        form.enterDate(secondMeetingDate);
        form.submitForm();
        // check and submit replan
        notifications.getReplanTitle().shouldHave(text(replanText), Duration.ofSeconds(3));
        notifications.clickReplanButton();
        // check success
        notifications.getSuccessTitle().shouldHave(text(successText), Duration.ofSeconds(3));
        notifications.getSuccessContent().shouldHave(text(secondMeetingDate));
    }
}
