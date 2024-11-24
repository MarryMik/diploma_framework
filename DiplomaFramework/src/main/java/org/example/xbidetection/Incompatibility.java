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
        return detectedDifference.getTestWebElement();
    }

    public String getFilePath(){
        return detectedDifference.getVisDiff().getTestScreenshot().getPath();
    }

    public String getFilePathScreenshot2(){
        return detectedDifference.getVisDiff().getPathToNewScreenshot();
    }


//    public String getTypeXBI(){
//
//    }

    public String getBrowserName(){
        return detectedDifference.getVisDiff().getTestScreenshot().getBrowserName();
    }

    public String getPageName(){
        return detectedDifference.getVisDiff().getTestScreenshot().getPageName();
    }
}
