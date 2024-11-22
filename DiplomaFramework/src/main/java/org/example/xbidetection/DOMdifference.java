package org.example.xbidetection;

import org.openqa.selenium.WebElement;

import java.util.List;

public class DOMdifference {
    private String type;
    private String ParameterName;
    private WebElement webElement;
    private String parameterValue;
    private String parameterValue2;
    private VisualDifference visDiff;

    public DOMdifference(VisualDifference visDiff, String ParameterName, WebElement webElement, String parameterValue, String parameterValue2) {
        this.visDiff = visDiff;
        this.ParameterName = ParameterName;
        this.webElement = webElement;
        this.parameterValue = parameterValue;
        this.parameterValue2 = parameterValue2;
    }

    private void classifyDiffernce(){

    }

//    public List<Incompatibility> detectXBI(){ Incompatibility i1 = new Incompatibility(this) }

    public String getType() {
        return type;
    }

    public String getParameterName() {
        return ParameterName;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public String getParameterValue2() {
        return parameterValue2;
    }

    public VisualDifference getVisDiff() {
        return visDiff;
    }

}
