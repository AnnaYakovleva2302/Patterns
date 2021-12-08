package ru.netology.card.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RecipientDataGenerator {
    private RecipientDataGenerator() {
    }

    public static String generateDate(int days) {
        LocalDate local = LocalDate.now();
        local = local.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return local.format(formatter);
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().cellPhone();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(
                RecipientDataGenerator.generateCity(locale),
                RecipientDataGenerator.generateName(locale),
                RecipientDataGenerator.generatePhone(locale)
            );
            return user;
        }
    }
}
