package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecordPage extends BasePage{
    private final String procedureEditButtonXpath = "//div[@class=\"procedure-card__buttns-wrap\"]/button[@class=\"buttn_edit\"]";


    public RecordPage() {}

    public void openProcedureEditPage(){
        driver.findElement(By.xpath(this.procedureEditButtonXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/procedure"));
    }
}
