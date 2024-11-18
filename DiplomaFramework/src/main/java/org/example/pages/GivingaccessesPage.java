package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GivingaccessesPage extends BasePage{
    private final String diaryPageLinkId = "diary_page_link";

    public GivingaccessesPage() {}

    public void openTeethDiaryPage() {
        driver.findElement(By.id(diaryPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/diary"));
    }

}
