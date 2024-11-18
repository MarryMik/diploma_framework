package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccessesPage extends BasePage{
    private final String exitButtonDocId = "exit_doc_button";

    public AccessesPage() {}

    public void logout(){
        driver.findElement(By.id(exitButtonDocId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/"));
    }

}
