package org.example.pages;

import static org.example.core.WebDriverInstance.driverInstance;
import static org.example.utils.Utils.sleep;

import org.example.xbidetection.Screenshot;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {}

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void pageUp(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        sleep(2000);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getDriverName() {
        Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
        return capabilities.getBrowserName();
    }

    public Screenshot takeScreenshot(String path) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        File outputfile = new File(path);
        ImageIO.write(bufferedImage, "png", outputfile);
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return new Screenshot(path, driver.getTitle(), capabilities.getBrowserName(), driver);
    }

    public String getBrowserName() {
        Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
        return capabilities.getBrowserName();
    }

}
