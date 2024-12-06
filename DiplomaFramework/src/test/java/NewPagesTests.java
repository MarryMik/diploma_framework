import org.apache.log4j.Logger;
import org.example.xbidetection.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Utils.sleep;

public class NewPagesTests extends BaseTest{
    private static final Logger log = Logger.getLogger(NewPagesTests.class);

//    WebDriver driver = new ChromeDriver();
//
//    // Встановлення розміру вікна
//        driver.manage().window().setSize(new Dimension(800, 600));
//
//    // Відкриття сторінки
//        driver.get("https://example.com");
    @Test
    public void detectXBITest() throws IOException, JSONException{
        String methodName = "SSIM/";
        Report report = new Report("All pages XBI detection","src/test/java/testdata/reports/newReport.json");
        String reportFilesPath ="src/test/java/testdata/reports/files/";
        log.info("Google Chrome");
        driver1.get(baseUrl);
        sleep(2000);
        log.info("set driver to pages");
        mainPage.setDriver(driver1);
        requestPage.setDriver(driver1);

        log.info("Take baseline screenshots");
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshot = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(mainPageScreenshot);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(requestPageScreenshot);
        requestPage.openRegisterPage();

        log.info("FireFox");
        driver2.get(baseUrl);

        log.info("set driver to pages");
        mainPage.setDriver(driver2);
        requestPage.setDriver(driver2);

        log.info("Take testing screenshots");
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshotT = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+methodName+testingScreenshotName);
        testingScreenshots.add(mainPageScreenshotT);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot2 = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+methodName+testingScreenshotName);
        testingScreenshots.add(requestPageScreenshot2);
        requestPage.openRegisterPage();
        //----
        log.info("MicrosoftEdge");
        driver3.get(baseUrl);

        log.info("set driver to pages");
        mainPage.setDriver(driver3);
        requestPage.setDriver(driver3);

        log.info("Take testing screenshots");
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshotT2 = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+methodName+testingScreenshotName2);
        testingScreenshots2.add(mainPageScreenshotT2);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshotT2 = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+methodName+testingScreenshotName2);
        testingScreenshots2.add(requestPageScreenshotT2);
        requestPage.openRegisterPage();

        log.info("Compare baseline screenshots to testing screenshot");
        log.info("Compare with screenshot from FireFox");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare("SSIM");
        log.info("Vdiff size "+visualDifferences.size());

        log.info("Compare with screenshot from MicrosoftEdge");
        Comparison comparison2 = new Comparison("GoogleChrome", "MicrosoftEdge");
        comparison2.setTestScreenshots(testingScreenshots2);
        comparison2.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences2 = comparison2.compare("SSIM");
        log.info("Vdiff size "+visualDifferences2.size());

        List<VisualDifference> allVissDifferences = new ArrayList<>();
        allVissDifferences.addAll(visualDifferences);
        allVissDifferences.addAll(visualDifferences2);

        log.info("Detect DOM differences (from FireFox)");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : allVissDifferences) {
            DOMdifference domDiff = visualDifference.detectDOMDifference();
            doMdifferences.add(domDiff);
            j++;
        }
        log.info("DOM size "+doMdifferences.size());

//        log.info("Detect DOM differences (from MicrosoftEdge)");
//        List <DOMdifference> doMdifferences2 = new ArrayList<>();
//        j =0;
//        for(VisualDifference visualDifference : visualDifferences2) {
//            DOMdifference domDiff = visualDifference.detectDOMDifference();
//            doMdifferences2.add(domDiff);
//            j++;
//        }
//        log.info("DOM size "+doMdifferences2.size());

        log.info("Detect incompatibilities (From FireFox)");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }

//        log.info("Detect incompatibilities (From MicrosoftEdge)");
//        List <Incompatibility> incompatibilities2 = new ArrayList<>();
//        for(DOMdifference domDifference : doMdifferences2) {
//            List <Incompatibility> i = domDifference.detectXBI();
//            incompatibilities2.addAll(i);
//        }

        log.info("create report");
        report.addIncompatibilities(incompatibilities);
//        report.addIncompatibilities(incompatibilities2);
        report.createReport();
        report.saveReport();
        report.showReport();
    }
}
