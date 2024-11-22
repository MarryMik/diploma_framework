package org.example.xbidetection;

import org.openqa.selenium.WebElement;

public class Incompatibility {
    private final DOMdifference detectedDifference;

    public Incompatibility(DOMdifference detectedDifference) {
        this.detectedDifference = detectedDifference;
    }

    public DOMdifference getDetectedDifference() {
        return detectedDifference;
    }

    public WebElement getWebElement(){
        return detectedDifference.getWebElement();
    }

    public String getFilePath(){
        return detectedDifference.getVisDiff().getScreenshot().getPath();
    }

    public String getTypeXBI(){
        return detectedDifference.getType();
    }

    public String getBrowserName(){
        return detectedDifference.getVisDiff().getScreenshot().getBrowserName();
    }

    public String getPageName(){
        return detectedDifference.getVisDiff().getScreenshot().getPageName();
    }
}
