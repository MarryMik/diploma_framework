package org.example.core;

import org.openqa.selenium.WebDriver;

public class WebDriverInstance {
    private static WebDriverInstance instance;
    private WebDriver driver1;
    private WebDriver driver2;
    private WebDriver driver3;
    private WebDriverInstance() {}

    public static WebDriverInstance driverInstance(){
        if(instance == null){
            instance = new WebDriverInstance();
        }
        return instance;
    }

    public void setDriver1(WebDriver driver) {
        this.driver1 = driver;
    }

    public void setDriver2(WebDriver driver) {
        this.driver2 = driver;
    }

    public void setDriver3(WebDriver driver) {
        this.driver3 = driver;
    }

    public WebDriver getDriver(String browsername) {
        switch (browsername) {
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
    public WebDriver getDriver1(){
        return this.driver1;
    }

    public WebDriver getDriver2(){
        return this.driver2;
    }

    public WebDriver getDriver3(){
        return this.driver3;
    }
}
