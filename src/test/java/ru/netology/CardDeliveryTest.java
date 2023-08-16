package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {


    LocalDate currentDate = LocalDate.now();


    public String dateGenerator(int date) {
        String format = LocalDate.now().plusDays(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return format;
    }


    @BeforeEach
    public void openLocalhost() {
        open("http://localhost:9999");
        $("[data-test-id='date'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL,"a" + Keys.DELETE);

    }

    @Test
    void successfulOrderCardTest() {

        $("[data-test-id='city'] [placeholder='Город']").setValue("Москва");
        $("[data-test-id='date'] [placeholder='Дата встречи']").val(dateGenerator(5));
        $("[data-test-id='name'] [name='name']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [name='phone']").setValue("+79850000000");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateGenerator(5)), Duration.ofSeconds(15)).shouldBe(visible);

    }
}