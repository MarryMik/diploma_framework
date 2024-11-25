package org.example.pages;

import static org.example.core.WebDriverInstance.driverInstance;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
       this.driver = null;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    public String getDriverName(){
        Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
        return capabilities.getBrowserName();
    }
    public File takeScreenshot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public String getBrowserName(){
        Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
        return capabilities.getBrowserName();
    }

    public void detectIncompatibilities(){
        //////....
    }
}
