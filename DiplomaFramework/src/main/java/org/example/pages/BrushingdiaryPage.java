package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrushingdiaryPage extends BasePage{
    private final String givingAccessPageLinkId = "giving_access_page_link";
    private final String morningBrushingCheckBoxId = "brushing_checkbox_morning";

    public BrushingdiaryPage() {}

    public void openGivingAccessPage() {
        driver.findElement(By.id(givingAccessPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/giving_accesses_page"));
    }

    public void clickOnCheckbox(){
        driver.findElement(By.id(morningBrushingCheckBoxId)).click();
    }
}
