package org.example.pages;

import org.example.models.UserInfo;
import org.example.utils.IUserInfoReader;
import org.example.utils.JsonFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppointmentjournalPage extends BasePage{
    private final String accessesPageLinkId = "accesses_page_link";
    private final String newAppointButtonXpath = "//button[@class=\"shedule-appointment\" and @id=\"none\"]";
    private final String nameAppointInputId = "name_appoint_input";
    private final String phoneAppointInputId = "phone_appoint_input";
    private final String procedureAppointInputId = "procedure_appoint_input";
    private final String commentAppointInputId = "comment_appoint_input";

    IUserInfoReader fileReader = new JsonFileReader();
    UserInfo userPatientInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userPatientTestData.json");

    public AppointmentjournalPage() {}

    public void openLoginPage(){
        driver.findElement(By.id(accessesPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/accesses"));
    }

    public void openAppointWindow(){
        driver.findElement(By.xpath(this.newAppointButtonXpath)).click();
        driver.findElement(By.id(this.nameAppointInputId)).sendKeys(userPatientInfo.getUserName());
        driver.findElement(By.id(phoneAppointInputId)).sendKeys(userPatientInfo.getUserPhone());
        driver.findElement(By.id(procedureAppointInputId)).sendKeys("Консультація");
        driver.findElement(By.id(commentAppointInputId)).sendKeys("Запізнення на 10 хвилин");
    }
}
