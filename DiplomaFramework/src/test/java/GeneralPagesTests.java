import org.apache.log4j.Logger;
import org.example.xbidetection.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Utils.sleep;

public class GeneralPagesTests extends BaseTest{
    private static final Logger log = Logger.getLogger(GeneralPagesTests.class);

    private List<Screenshot> takeScreenshots(WebDriver driver1, String reportFilesPath,String baselineScreenshotName, String methodName ) throws IOException {

        log.info("set driver to pages");
        accessesPage.setDriver(driver1);
        accountPage.setDriver(driver1);
        appointmentjournalPage.setDriver(driver1);
        brushdiaryPage.setDriver(driver1);
        brushingdiaryPage.setDriver(driver1);
        givingaccessesPage.setDriver(driver1);
        loginPage.setDriver(driver1);
        mainPage.setDriver(driver1);
        procedurePage.setDriver(driver1);
        recordPage.setDriver(driver1);
        registerPage.setDriver(driver1);
        requestPage.setDriver(driver1);
        simptomdiaryPage.setDriver(driver1);
        teethdiaryPage.setDriver(driver1);
        treatmentPage.setDriver(driver1);
        loginSteps.setDriver(driver1);

        log.info("Take baseline screenshots");
        //String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        driver1.navigate().refresh();
        Screenshot mainPageScreenshot = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(mainPageScreenshot);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        driver1.navigate().refresh();
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(requestPageScreenshot);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        driver1.navigate().refresh();
        registerPage.pageUp();
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshot = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(registerPageScreenshot);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.pageUp();
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshot = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(loginPageScreenshot);
        loginPage.login();

        log.info("Screenshot of account page");
        accountPage.pageUp();
        Screenshot accountPageDoctorScreenshot = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(accountPageDoctorScreenshot);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        driver1.navigate().refresh();
        appointmentjournalPage.pageUp();;
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshot = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(appointmentPageScreenshot);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        driver1.navigate().refresh();
        accessesPage.pageUp();
        Screenshot accessesPageScreenshot = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(accessesPageScreenshot);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        driver1.navigate().refresh();
        brushdiaryPage.pageUp();
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshot = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(brushPageScreenshot);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();
///
        log.info("Screenshot of symptom page");
        driver1.navigate().refresh();
        simptomdiaryPage.pageUp();
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshot = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(symptomPageScreeenshot);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        driver1.navigate().refresh();
        brushingdiaryPage.pageUp();
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshot = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(brushingPageScreenshot);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        driver1.navigate().refresh();
        givingaccessesPage.pageUp();
        Screenshot givingaccessesPageScreenshot = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(givingaccessesPageScreenshot);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshot of teethdiary page");
        driver1.navigate().refresh();
        teethdiaryPage.pageUp();
        Screenshot teethdiaryPageScreenshot1 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/"+methodName+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot1);
        teethdiaryPage.clickOnFilterWindowButton();

        return baselineScreenshots;
    }

    @Test
    public void detectXBIonAllPagesByPixels() throws IOException, JSONException {
        String methodName = "bypixel/";
        String method = "pixels";
        Report report = new Report("All pages XBI detection","src/test/java/testdata/reports/reportPixels.json");
        String reportFilesPath = "src/test/java/testdata/reports/files/";

        log.info("Google Chrome");
        driver1.get(baseUrl);
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = takeScreenshots(driver1,reportFilesPath,baselineScreenshotName,methodName);

        log.info("FireFox");
        driver2.get(baseUrl);
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = takeScreenshots(driver2,reportFilesPath,testingScreenshotName, methodName);

        log.info("MicrosoftEdge");
        driver3.get(baseUrl);
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = takeScreenshots(driver3,reportFilesPath,testingScreenshotName2, methodName);

        log.info("Compare baseline screenshots to testing screenshot");
        log.info("Compare with screenshot from FireFox");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare(method);
        /////////////////////////
        log.info("Compare with screenshot from MicrosoftEdge");
        Comparison comparison2 = new Comparison("GoogleChrome", "MicrosoftEdge");
        comparison2.setTestScreenshots(testingScreenshots2);
        comparison2.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences2 = comparison2.compare(method);
        /////////////////
        List<VisualDifference> allVissDifferences = new ArrayList<>();
        allVissDifferences.addAll(visualDifferences);
        allVissDifferences.addAll(visualDifferences2);
        /////////////////////////

        log.info("Detect DOM differences");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : allVissDifferences) {
            DOMdifference domDiff = visualDifference.detectDOMDifference();
            doMdifferences.add(domDiff);
            j++;
        }
        //////////////
        log.info("Detect incompatibilities");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }
        /////////////
        log.info("create report");
        report.addIncompatibilities(incompatibilities);
        report.createReport();
        report.saveReport();
        report.showReport();
    }
    @Test
    public void detectXBIonAllPagesBySSIM() throws IOException, JSONException {
        String methodName = "SSIM/";
        String method = "SSIM";
        Report report = new Report("All pages XBI detection","src/test/java/testdata/reports/reportSSIM.json");
        String reportFilesPath = "src/test/java/testdata/reports/files/";

        log.info("Google Chrome");
        driver1.get(baseUrl);
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = takeScreenshots(driver1,reportFilesPath,baselineScreenshotName, methodName);

        log.info("FireFox");
        driver2.get(baseUrl);
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = takeScreenshots(driver2,reportFilesPath,testingScreenshotName, methodName);

        log.info("MicrosoftEdge");
        driver3.get(baseUrl);
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = takeScreenshots(driver3,reportFilesPath,testingScreenshotName2, methodName);

        log.info("Compare baseline screenshots to testing screenshot");
        log.info("Compare with screenshot from FireFox");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare(method);
        /////////////////////////
        log.info("Compare with screenshot from MicrosoftEdge");
        Comparison comparison2 = new Comparison("GoogleChrome", "MicrosoftEdge");
        comparison2.setTestScreenshots(testingScreenshots2);
        comparison2.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences2 = comparison2.compare(method);
        /////////////////
        List<VisualDifference> allVissDifferences = new ArrayList<>();
        allVissDifferences.addAll(visualDifferences);
        allVissDifferences.addAll(visualDifferences2);
        /////////////////////////

        log.info("Detect DOM differences");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : allVissDifferences) {
            DOMdifference domDiff = visualDifference.detectDOMDifference();
            doMdifferences.add(domDiff);
            j++;
        }
        //////////////
        log.info("Detect incompatibilities");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }
        /////////////
        log.info("create report");
        report.addIncompatibilities(incompatibilities);
        report.createReport();
        report.saveReport();
        report.showReport();
    }

    @Test
    public void detectXBIonAllPagesByCombo() throws IOException, JSONException {
        String methodName = "combo/";
        String method = "combo";
        Report report = new Report("All pages XBI detection","src/test/java/testdata/reports/reportCombo.json");
        String reportFilesPath = "src/test/java/testdata/reports/files/";

        log.info("Google Chrome");
        driver1.get(baseUrl);
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = takeScreenshots(driver1,reportFilesPath,baselineScreenshotName, methodName);

        log.info("FireFox");
        driver2.get(baseUrl);
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = takeScreenshots(driver2,reportFilesPath,testingScreenshotName, methodName);

        log.info("MicrosoftEdge");
        driver3.get(baseUrl);
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = takeScreenshots(driver3,reportFilesPath,testingScreenshotName2, methodName);

        log.info("Compare baseline screenshots to testing screenshot");
        log.info("Compare with screenshot from FireFox");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare(method);
        /////////////////////////
        log.info("Compare with screenshot from MicrosoftEdge");
        Comparison comparison2 = new Comparison("GoogleChrome", "MicrosoftEdge");
        comparison2.setTestScreenshots(testingScreenshots2);
        comparison2.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences2 = comparison2.compare(method);
        /////////////////
        List<VisualDifference> allVissDifferences = new ArrayList<>();
        allVissDifferences.addAll(visualDifferences);
        allVissDifferences.addAll(visualDifferences2);
        /////////////////////////

        log.info("Detect DOM differences");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : allVissDifferences) {
            DOMdifference domDiff = visualDifference.detectDOMDifference();
            doMdifferences.add(domDiff);
            j++;
        }
        //////////////
        log.info("Detect incompatibilities");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }
        log.info("create report");
        report.addIncompatibilities(incompatibilities);
        report.createReport();
        report.saveReport();
        report.showReport();
    }

}
