package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TreatmentPage extends BasePage{

    private final String recordEditButtonXpath ="//div[@class=\"record-card__right\"]/div/button[@class=\"buttn_edit\"]";

    public TreatmentPage(){}

    public void openRecordEditPage(){
        driver.findElement(By.xpath(this.recordEditButtonXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/record"));
    }
}
