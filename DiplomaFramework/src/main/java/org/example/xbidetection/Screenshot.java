package org.example.xbidetection;

import nu.pattern.OpenCV;
import org.opencv.core.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Screenshot {
    static {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        OpenCV.loadLocally();
    }

    private final String path;
    private final String pageName;
    private final String browserName;
    private final WebDriver driver;
    private String size;

    public Screenshot(String path, String pageName, String browserName, WebDriver driver) {
        this.path = path;
        this.pageName = pageName;
        this.browserName = browserName;
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public String getPageName() {
        return pageName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    private File processContrast(File file) {



        return new File(path);
    }

    private File processBrightness(File file){


        return new File(path);
    }

    private File processNoises(File file){


        return new File(path);
    }

//    public List<Rect> detectCBD(String filepath){
//        Mat img1 = Imgcodecs.imread(path);
//        Mat img2 = Imgcodecs.imread(filepath);
//
//        // Check dimensions
//        if (img1.size().width != img2.size().width || img1.size().height != img2.size().height) {
//            throw new IllegalArgumentException("Images must have the same dimensions for comparison.");
//        }
//
//        // Compute the absolute difference between the images
//        Mat diff = new Mat();
//        Core.absdiff(img1, img2, diff);
//
//        // Convert to grayscale
//        Mat gray = new Mat();
//        Imgproc.cvtColor(diff, gray, Imgproc.COLOR_BGR2GRAY);
//
//        // Threshold the differences
//        Mat thresh = new Mat();
//        Imgproc.threshold(gray, thresh, 50, 255, Imgproc.THRESH_BINARY);
//
//        // Find contours of differences
//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat hierarchy = new Mat();
//        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//
//        // Highlight differences and extract coordinates
//        List<Rect> differences = new ArrayList<>();
//        for (MatOfPoint contour : contours) {
//            Rect boundingRect = Imgproc.boundingRect(contour);
//            differences.add(boundingRect);
//
//            // Highlight difference on the original image
//            Imgproc.rectangle(img1, boundingRect, new Scalar(0, 0, 255), 2);
//        }
//
//        // Save the highlighted image
//        Imgcodecs.imwrite(outputDiffPath, img1);
//
//        return differences;
//
//        return Arrays.asList(23.8, 0.0);
//    }

//    public void detectCBD2(String filepath){

//        // Load images
//        Mat img1 = Imgcodecs.imread(imagePath1);
//        Mat img2 = Imgcodecs.imread(imagePath2);
//
//        // Check dimensions
//        if (img1.size().width != img2.size().width || img1.size().height != img2.size().height) {
//            throw new IllegalArgumentException("Images must have the same dimensions for comparison.");
//        }
//
//        // Compute the absolute difference
//        Mat diff = new Mat();
//        Core.absdiff(img1, img2, diff);
//
//        // Convert the difference to grayscale
//        Mat gray = new Mat();
//        Imgproc.cvtColor(diff, gray, Imgproc.COLOR_BGR2GRAY);
//
//        // Normalize the differences to create a heatmap
//        Mat heatmap = new Mat();
//        Core.normalize(gray, heatmap, 0, 255, Core.NORM_MINMAX);
//
//        // Apply a color map to the heatmap
//        Mat coloredHeatmap = new Mat();
//        Imgproc.applyColorMap(heatmap, coloredHeatmap, Imgproc.COLORMAP_JET);
//
//        // Save the heatmap
//        Imgcodecs.imwrite(outputHeatmapPath, coloredHeatmap);

//    }

    public List<VisualDifference> detectCBD(Screenshot scr2){
        Mat baselineImage = Imgcodecs.imread(scr2.getPath());
        Mat testImage = Imgcodecs.imread(path);
        int minHeight = Math.min(baselineImage.height(), testImage.height());
        int minWidth = Math.min(baselineImage.width(), testImage.width());
        Rect cropRect = new Rect(0, 0, minWidth, minHeight);
        System.out.println("Height baseline - "+baselineImage.height()+" test - "+testImage.height());
        System.out.println("Width baseline - "+baselineImage.width()+" test - "+testImage.width());

        if(baselineImage.rows()<testImage.rows()){
            testImage = testImage.submat(cropRect);
        }else{
            baselineImage = baselineImage.submat(cropRect);
        }
        if(baselineImage.cols()<testImage.cols()){
            testImage = testImage.submat(cropRect);
        }else{
            baselineImage = baselineImage.submat(cropRect);
        }
        this.size = "("+minWidth+" x "+minHeight+")";
        // Convert to grayscale
        Mat baselineGray = new Mat();
        Mat testGray = new Mat();
        Imgproc.cvtColor(baselineImage, baselineGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(testImage, testGray, Imgproc.COLOR_BGR2GRAY);

        // Compute absolute difference
        Mat diff = new Mat();
        Core.absdiff(baselineGray, testGray, diff);

        // Threshold the difference image
        Mat thresholded = new Mat();
        Imgproc.threshold(diff, thresholded, 50, 255, Imgproc.THRESH_BINARY);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresholded, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Extract bounding boxes
        List<VisualDifference> visualDiffs = new ArrayList<>();
        //List<Rect> boundingBoxes = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Rect boundingBox = Imgproc.boundingRect(contour);
            //boundingBoxes.add(boundingBox);
            VisualDifference vd = new VisualDifference(this, scr2, boundingBox);
            visualDiffs.add(vd);
        }
        return visualDiffs;
    }

    public String getSize() {
        return size;
    }


//    public Screenshot processScreenshot(){
//        File afterContrast = processContrast(new File(path));
//        File afterBrightness = processBrightness(afterContrast);
//        File afterNoises = processNoises(afterBrightness);
//        return new Screenshot(afterNoises.getPath(), pageName, browserName);
//    }

}
