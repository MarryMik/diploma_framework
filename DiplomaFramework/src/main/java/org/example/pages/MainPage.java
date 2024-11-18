package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.example.utils.Utils.sleep;

public class MainPage extends BasePage{
    private final String requestPageLinkId = "request_page_link";
    private final String loginPageButtonId ="login_button_link";
    private final String registerPageButtonId ="register_button_link";
    private final String logoId ="logo";

    public MainPage(){}

    public void openRequestPage(){
        driver.findElement(By.id(requestPageLinkId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/request_page"));
    }

    public void openLoginPage(){
        driver.findElement(By.id(loginPageButtonId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/login"));
    }

    public void hoverLogo(){
        WebElement logo = driver.findElement(By.id(logoId));
        Actions actions = new Actions(driver);
        actions.moveToElement(logo).perform();
        sleep(5000);
    }
}
