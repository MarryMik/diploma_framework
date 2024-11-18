package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage{
    private final String appointmentPageLinkId = "appointment_page_link";
    private final String brushesPageLinkId = "brushes_page_link";

    public AccountPage(){}

    public void openAppointmentPage(){
        driver.findElement(By.id(appointmentPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/appointments"));
    }

    public void openBrushesPage(){
        driver.findElement(By.id(brushesPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/brushes"));
    }
}
