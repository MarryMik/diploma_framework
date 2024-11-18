package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Utils.sleep;

public class BrushdiaryPage extends BasePage {
    private final String symptomsPageLinkId = "symptoms_page_link";
    private final String buttonOpenXpath ="//button[@class=\"active-brush__buttn-add\" and text()=\"Додати\"]";
    private final String buttonCloseXpath = "//button[@class=\"active-brush__buttn-add\" and text()=\"Закрити\"]";
    private final String hardnessInputId = "hardness_input";
    private final String colorInputId = "color_input";
    private final String brushDateInputId = "brush_date_input";

    public BrushdiaryPage() {}

    public void openSymptomsPage() {
        driver.findElement(By.id(symptomsPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/symptoms"));
    }

    public void openNewBrushWindow(){
        driver.findElement(By.xpath(this.buttonOpenXpath)).click();
        sleep(1000);
        driver.findElement(By.id(hardnessInputId)).sendKeys("м'яка");
        driver.findElement(By.id(colorInputId)).sendKeys("зелений");
        WebElement  brushDateInput = driver.findElement(By.id(brushDateInputId));
        brushDateInput.clear();
        brushDateInput.sendKeys("18.11.2024");
    }

    public void closeBrushWindoe(){
        driver.findElement(By.xpath(this.buttonCloseXpath)).click();
    }

}
