package org.example.xbidetection;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opencv.core.Rect;

import java.util.List;

public class VisualDifference {
    private Screenshot testScreenshot;
    private Screenshot baseScreenshot;
    private String methodName;
    private Rect boundingBox;
    private String pathToNewScreenshot;



    public VisualDifference(Screenshot testScreenshot, Screenshot baseScreenshot, Rect boundingBox) {
        this.testScreenshot = testScreenshot;
        this.baseScreenshot = baseScreenshot;
        this.boundingBox = boundingBox;
    }

    public Screenshot getTestScreenshot() {
        return testScreenshot;
    }

    public Screenshot getBaselineScreenshot() {
        return baseScreenshot;
    }

    public String getPathToNewScreenshot(){
        return pathToNewScreenshot;
    }

    public Rect getBoundingBox() {
        return boundingBox;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private WebElement identifyWebElement(){
        WebDriver driver = testScreenshot.getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long x = (long) this.boundingBox.x;
        Long y = (long) this.boundingBox.y;
        try {
            WebElement element = (WebElement) jsExecutor.executeScript(
                    "return document.elementFromPoint(arguments[0], arguments[1]);", x, y);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    private String classifyDifference(Rect rect){
        if (rect.width * rect.height > 10000) {
            return "Large Layout Issue";
        } else if (rect.width > rect.height) {
            return "Horizontal Alignment Issue";
        } else if (rect.height > rect.width) {
            return "Vertical Alignment Issue";
        } else {
            return "Minor Visual Glitch";
        }
    }

    private void drawCMD(String outputDiffPath){
        Mat img1 = Imgcodecs.imread(testScreenshot.getPath());
        Imgproc.rectangle(img1, boundingBox, new Scalar(0, 0, 255),2);
        Imgcodecs.imwrite(outputDiffPath, img1);
        this.pathToNewScreenshot = outputDiffPath;
    }

    public DOMdifference detectDOMDifference(String outputDiffPath){
        String category = classifyDifference(boundingBox);
        WebElement element = identifyWebElement();

        //add Log!
        String elementDescription;
        if(element == null){
            elementDescription = element.getTagName() + " (" + element.getText() + ")";
        }else{
            elementDescription = "element is not found";
        }

        drawCMD(outputDiffPath);
        DOMdifference domDiff = new DOMdifference(this,element, category);
        return domDiff;
    }

}
