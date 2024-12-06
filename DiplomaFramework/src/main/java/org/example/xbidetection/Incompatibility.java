package org.example.xbidetection;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Incompatibility {
    private DOMdifference detectedDifference;
    private String issueType;
    private String details;
    private String subIssueType;

    public Incompatibility(String issueType) {
        this.issueType = issueType;
        this.subIssueType= "немає";
    }

    public Incompatibility(String issueType, String details, String subIssueType) {
        this.issueType = issueType;
        this.details = details;
        this.subIssueType = subIssueType;
    }

    public String getSubIssueType() {
        return subIssueType;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public DOMdifference getDetectedDifference() {
        return detectedDifference;
    }

    public void setDetectedDifference(DOMdifference detectedDifference) {
        this.detectedDifference = detectedDifference;
    }

    public VisualDifference getVisualDifference() {
        return detectedDifference.getVisDiff();
    }

    public String getWebElementTagName() {
        if (detectedDifference.getTestWebElement() != null) {
            return detectedDifference.getTestWebElement().getTagName();
        } else {
            return "Елемента не знайдено";
        }
    }

    public String getFilePath() {
        return detectedDifference.getVisDiff().getTestScreenshot().getPath();
    }

    public String getBrowserName() {
        return detectedDifference.getVisDiff().getTestScreenshot().getBrowserName();
    }

    public String getPageName() {
        return detectedDifference.getVisDiff().getTestScreenshot().getPageName();
    }

    public String getBrowserVersion() {
        WebDriver driver = detectedDifference.getVisDiff().getTestScreenshot().getDriver();
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return capabilities.getBrowserVersion();
    }

    public String getVisualDiffType(){
        return detectedDifference.getCategory();
    }

    public String getBaselineBrowserName(){
        return detectedDifference.getVisDiff().getBaselineScreenshot().getBrowserName();
    }

    public String getBaseLineBrowserVersion(){
        WebDriver driver = detectedDifference.getVisDiff().getBaselineScreenshot().getDriver();
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return capabilities.getBrowserVersion();
    }

    public String getMethodName(){
        return detectedDifference.getVisDiff().getMethodName();
    }
}
