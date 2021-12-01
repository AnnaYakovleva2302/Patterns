package ru.netology.card;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class RecipientData {
    private String name;
    private String phone;
    private String city;
    private String date;

    public RecipientData() {
        Faker faker = new Faker(new Locale("ru"));
        name = faker.name().fullName();
        phone = faker.phoneNumber().cellPhone();
        city = faker.address().city();
        date = getDate(3);
    }

    public String getDate(Integer days) {
        LocalDate local = LocalDate.now();
        local = local.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date = local.format(formatter);
        return date;
    }

    public String getDate() {
        return date;
    }
}
