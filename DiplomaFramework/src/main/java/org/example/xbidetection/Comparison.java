package org.example.xbidetection;

import java.util.ArrayList;
import java.util.List;

public class Comparison {
    private List<Screenshot> baselineScreenshots;
    private List<Screenshot> testScreenshots;
    private String baselineBrowser;
    private String testBrowser;

    public Comparison(String baselineBrowser, String testBrowser) {
        this.baselineBrowser = baselineBrowser;
        this.testBrowser = testBrowser;
    }

    public List<Screenshot> getBaselineScreenshots() {
        return baselineScreenshots;
    }

    public void setBaselineScreenshots(List<Screenshot> baselineScreenshots) {
        this.baselineScreenshots = baselineScreenshots;
    }

    public List<Screenshot> getTestScreenshots() {
        return testScreenshots;
    }

    public void setTestScreenshots(List<Screenshot> testScreenshots) {
        this.testScreenshots = testScreenshots;
    }

    public String getBaselineBrowser() {
        return baselineBrowser;
    }

    public String getTestBrowser() {
        return testBrowser;
    }

    public void addTestScreenshot(Screenshot testScreenshot) {
        testScreenshots.add(testScreenshot);
    }

    public void addBaselineScreenshot(Screenshot baselineScreenshot) {
        baselineScreenshots.add(baselineScreenshot);
    }

   public List<VisualDifference> compare(){
        List<VisualDifference> differences = new ArrayList<VisualDifference>();
        System.out.println("comparison");
        for(int i =0; i < testScreenshots.size(); i++){
            differences.addAll(testScreenshots.get(i).detectCBD(baselineScreenshots.get(i)));
        }
        return differences;
   }
}
