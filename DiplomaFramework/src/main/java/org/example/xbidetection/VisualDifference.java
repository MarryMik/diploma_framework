package org.example.xbidetection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class VisualDifference {
    private Screenshot screenshot;
    private double coordinateX;
    private double coordinateY;
    private String methodName;

    public VisualDifference(Screenshot screenshot, double coordinateX, double coordinateY) {
        this.screenshot = screenshot;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Screenshot getScreenshot() {
        return screenshot;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

//    private WebElement identifyWebElement(Webdriver driver){
//
//    }

    private List<DOMdifference> compareWebElement(WebDriver driver2){
        List<DOMdifference> differences = new ArrayList<DOMdifference>();
        return differences;
    }

//    public List<DOMdifference> detectDOMDifference(WebDriver driver1, WebDriver driver2){
//        //identifyWebElement(driver1);
//        //List<DOMdifference> domdiff=compareWebElement(driver2);
//        //return domdiff;
//    }

}
