package org.example.core;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.core.WebDriverInstance.driverInstance;

public class MyDriver {

    private static final Logger log = Logger.getLogger(MyDriver.class);
    WebDriver driver1;
    WebDriver driver2;
    WebDriver driver3;

    public MyDriver() {
        this.driver1 = driverInstance().getDriver1();
        this.driver2 = driverInstance().getDriver2();
        this.driver3 = driverInstance().getDriver3();
    }

    private WebDriver getDriver(String browserName){
        switch(browserName){
            case "chrome":
                return driver1;
            case "firefox":
                return driver2;
            case "edge":
                return driver3;
            default:
                return driver1;
        }
    }

    public void click(String locator, String browserName){
        WebDriver driver = getDriver(browserName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        log.info("Click on the element('"+locator+"') in the "+browserName+" browser");
        element.click();
    }

    public void sendKeys(String locator, String text, String browserName){
        WebDriver driver = getDriver(browserName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        log.info("Populate element('"+locator+"') with text: "+text.trim()+"in the "+browserName+" browser");
        element.sendKeys(text.trim());
    }
}
