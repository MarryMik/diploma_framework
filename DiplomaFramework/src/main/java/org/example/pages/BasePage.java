package org.example.pages;

import static org.example.core.WebDriverInstance.driverInstance;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
       this.driver = null;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public String getDriverName(){
        Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
        return capabilities.getBrowserName();
    }
}
