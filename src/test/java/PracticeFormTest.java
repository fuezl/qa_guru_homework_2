import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void positiveFillTest() {
        //Arrange
        open("/automation-practice-form");

        //Act
        $("input#firstName").setValue("Ivan");
        $("input#lastName").setValue("Ivanov");
        $("input#userEmail").setValue("ivan.ivanov@gmail.com");
        $("label[for='gender-radio-1']").click();
        $("input#userNumber").setValue("9251116644");
        $("input#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText("1990");
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__month>div:nth-child(2)>div:nth-child(5)").click();
        $(".subjects-auto-complete__value-container").click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/Mr_beans.jpg"));
        $("#currentAddress").setValue("Russia");
        $("input#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").shouldBe(Condition.enabled).setValue("Noida").pressEnter();
        $("#submit").click();

        //Assert
        $(".table-hover>tbody>tr:nth-child(1)>td:nth-child(2)").shouldHave(matchText("Ivan Ivanov"));
        $(".table-hover>tbody>tr:nth-child(2)>td:nth-child(2)").shouldHave(matchText("ivan.ivanov@gmail.com"));
        $(".table-hover>tbody>tr:nth-child(4)>td:nth-child(2)").shouldHave(matchText("9251116644"));
    }
}
