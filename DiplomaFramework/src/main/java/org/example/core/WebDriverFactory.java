package org.example.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browser) {
        switch(browser){
            case "chrome":
                return configChrome();
            case "firefox":
                return configFirefox();
            case "edge":
                return configEdge();
            default:
                System.out.println("Unknown browser: " + browser);
                return configChrome();
        }
    }

    private static ChromeDriver configChrome(){
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static FirefoxDriver configFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static EdgeDriver configEdge() {
        EdgeOptions options = new EdgeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        EdgeDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
