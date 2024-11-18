package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SimptomdiaryPage extends BasePage{
    private  final String brushingPageLinkId = "brushing_page_link";
    private final String addSymptomButtonXpath ="//button[@class=\"symptom-history__buttn_add\" and text()=\"Додати\"]";
    private final String closeSymptomWindowButtonXpath ="//button[@class=\"symptom-history__buttn_add\" and text()=\"Закрити\"]";
    private final String symptomNameInputXpath ="//input[@name=\"symptom_name\"]";
    private final String symptomStartDateInputXpath ="//input[@name=\"symptom_datestart\"]";
    private final String symptomStartTimeInputXpath ="//input[@name=\"symptom_datestart\"]/following-sibling::input";
    private final String onePainPointDivXpath ="//div[@class=\"new-symptom__pain-wrap\"]/div";

    public SimptomdiaryPage() {}

    public void openBrushingPage(){
        driver.findElement(By.id(brushingPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/brushing"));
    }

    public void openSymptomWindow(){
        driver.findElement(By.xpath(this.addSymptomButtonXpath)).click();
        driver.findElement(By.xpath(this.symptomNameInputXpath)).sendKeys("Зубний біль");
        driver.findElement(By.xpath(this.symptomStartDateInputXpath)).sendKeys("18.11.2024");
        driver.findElement(By.xpath(this.symptomStartTimeInputXpath)).sendKeys("09:00");
        driver.findElement(By.xpath(this.onePainPointDivXpath)).click();
    }

    public void closeSymptomWindow(){
        driver.findElement(By.xpath(this.closeSymptomWindowButtonXpath)).click();
    }
}
