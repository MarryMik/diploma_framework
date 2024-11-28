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
        Report report = new Report("All pages XBI detection");
        String reportFilesPath ="src/test/java/testdata/reports/files/";
        log.info("Google Chrome");
        driver1.get(baseUrl);
        sleep(2000);
        log.info("set driver to pages");
        mainPage.setDriver(driver1);

        log.info("Take baseline screenshots");
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshot = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+baselineScreenshotName);
        baselineScreenshots.add(mainPageScreenshot);
        mainPage.openRequestPage();

        log.info("FireFox");
        driver2.get(baseUrl);

        log.info("set driver to pages");
        mainPage.setDriver(driver2);

        log.info("Take testing screenshots");
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshotT = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+testingScreenshotName);
        testingScreenshots.add(mainPageScreenshotT);
        mainPage.openRequestPage();

        log.info("Compare baseline screenshots to testing screenshot");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare();
        log.info("Vdiff size "+visualDifferences.size());
        log.info("Detect DOM differences");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : visualDifferences) {
            String nameOfFile = report.getNumber()+"_FF_cbd"+j+".png";
            String pageTitle = visualDifference.getTestScreenshot().getPageName();
            String path = reportFilesPath+pageTitle+"/cbd/"+nameOfFile;
            DOMdifference domDiff = visualDifference.detectDOMDifference(path);
            doMdifferences.add(domDiff);
            j++;
        }
        log.info("DOM size "+doMdifferences.size());
        log.info("Detect incompatibilities");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }
        log.info("size "+incompatibilities.size());
        log.info("create report");
        report.addIncompatibilities(incompatibilities);
        report.createReport();
        report.saveReport();
        report.showReport();


    }

}
