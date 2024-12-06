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

    private List<Mat> resizeScreenshots(Mat baselineImage, Mat testImage) {
        List<Mat> screenshots = new ArrayList<>();
        int minHeight = Math.min(baselineImage.height(), testImage.height());
        int minWidth = Math.min(baselineImage.width(), testImage.width());
        Rect cropRect = new Rect(0, 0, minWidth, minHeight);
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
        screenshots.add(baselineImage);
        screenshots.add(testImage);
        this.size = "("+minWidth+" x "+minHeight+")";
        return screenshots;
    }

    public List<VisualDifference> detectCBDbyPixels(Screenshot scr2){
        //зчитування скріншотів
        Mat baselineImage = Imgcodecs.imread(scr2.getPath());
        Mat testImage = Imgcodecs.imread(path);
        //коригування розміру зображень
        List<Mat> resizedScreenshots = resizeScreenshots(baselineImage, testImage);
        baselineImage = resizedScreenshots.get(0);
        testImage = resizedScreenshots.get(1);
        // Перетворення зображень у зображення з сірими відтінками
        Mat baselineGray = new Mat();
        Mat testGray = new Mat();
        Imgproc.cvtColor(baselineImage, baselineGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(testImage, testGray, Imgproc.COLOR_BGR2GRAY);

        // Підрахунок абсолютної різниці
        Mat diff = new Mat();
        Core.absdiff(baselineGray, testGray, diff);

        // Порогове зображення різниці
        Mat thresholded = new Mat();
        Imgproc.threshold(diff, thresholded, 50, 255, Imgproc.THRESH_BINARY);

        // пошук контурів
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresholded, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // витягнення обмежувальних рамок
        List<VisualDifference> visualDiffs = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Rect boundingBox = Imgproc.boundingRect(contour);
            VisualDifference vd = new VisualDifference(this, scr2, boundingBox);
            vd.setMethodName("bypixel");
            visualDiffs.add(vd);
        }
        return visualDiffs;
    }
    public List<VisualDifference> detectCBDbySSIM(Screenshot scr2){
        //зчитування скріншотів
        Mat baselineImage = Imgcodecs.imread(scr2.getPath());
        Mat testImage = Imgcodecs.imread(path);
        //коригування розміру зображень
        List<Mat> resizedScreenshots = resizeScreenshots(baselineImage, testImage);
        baselineImage = resizedScreenshots.get(0);
        testImage = resizedScreenshots.get(1);
        // Перетворення зображень у зображення з сірими відтінками
        Mat baselineGray = new Mat();
        Mat testGray = new Mat();
        Imgproc.cvtColor(baselineImage, baselineGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(testImage, testGray, Imgproc.COLOR_BGR2GRAY);
        // Розрахунок SSIM та отримання карти схожості
        Mat ssim = new Mat();
        Mat ssimMap = calculateSSIM(baselineGray, testGray, ssim);
        // Нормалізація карти SSIM для подальшої обробки
        Mat normalizedSSIM = new Mat();
        Core.normalize(ssimMap, normalizedSSIM, 0, 255, Core.NORM_MINMAX);
        normalizedSSIM.convertTo(normalizedSSIM, CvType.CV_8U);

        // Бінаризація (встановлюємо поріг схожості)
        Mat thresholdMap = new Mat();
        Imgproc.threshold(normalizedSSIM, thresholdMap, 200, 255, Imgproc.THRESH_BINARY_INV);

        // Знаходження контурів несумісностей
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(thresholdMap, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Виведення координат та побудова прямокутників
        List<VisualDifference> visualDiffs = new ArrayList<>();
        for (MatOfPoint contour : contours) {
            Rect boundingBox = Imgproc.boundingRect(contour);
            VisualDifference vd = new VisualDifference(this, scr2, boundingBox);
            vd.setMethodName("SSIM");
            visualDiffs.add(vd);
        }
        return visualDiffs;
    }

    private static Mat calculateSSIM(Mat img1, Mat img2, Mat ssimMap) {
        Mat mu1 = new Mat(), mu2 = new Mat(); // Середнє значення для обох зображень
        Mat sigma1_2 = new Mat(), sigma2_2 = new Mat(), sigma12 = new Mat(); // Дисперсії і коваріації

        // Розмивання для отримання середніх значень
        Imgproc.GaussianBlur(img1, mu1, new Size(11, 11), 1.5);
        Imgproc.GaussianBlur(img2, mu2, new Size(11, 11), 1.5);

        // Обчислення квадратів середніх значень
        Mat mu1_2 = new Mat(), mu2_2 = new Mat(), mu1_mu2 = new Mat();
        Core.multiply(mu1, mu1, mu1_2);
        Core.multiply(mu2, mu2, mu2_2);
        Core.multiply(mu1, mu2, mu1_mu2);

        // Розрахунок дисперсії
        Imgproc.GaussianBlur(img1.mul(img1), sigma1_2, new Size(11, 11), 1.5);
        Imgproc.GaussianBlur(img2.mul(img2), sigma2_2, new Size(11, 11), 1.5);
        Imgproc.GaussianBlur(img1.mul(img2), sigma12, new Size(11, 11), 1.5);

        // Формула SSIM
        Mat t1 = new Mat(), t2 = new Mat(), t3 = new Mat();
        double C1 = 6.5025, C2 = 58.5225; // Константи для стабільності

        Core.add(mu1_2, mu2_2, t1);
        Core.add(t1, new Scalar(C1), t1);

        Core.add(sigma1_2, sigma2_2, t2);
        Core.add(t2, new Scalar(C2), t2);

        Core.multiply(mu1_mu2, new Scalar(2), t3);
        Core.add(t3, new Scalar(C1), t3);

        Core.multiply(sigma12, new Scalar(2), ssimMap);
        Core.add(ssimMap, new Scalar(C2), ssimMap);

        Core.divide(t3, t1, t1);
        Core.divide(ssimMap, t2, t2);

        Core.multiply(t1, t2, ssimMap);

        // Обчислення середнього значення SSIM
        return ssimMap;
    }

    public String getSize() {
        return size;
    }
}
