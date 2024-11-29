import org.apache.log4j.Logger;
import org.example.xbidetection.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Utils.sleep;

public class MovementsTests extends BaseTest{
    private static final Logger log = Logger.getLogger(GeneralPagesTests.class);

    private List<Screenshot> takeScreenshots(WebDriver driver1, String reportFilesPath,String baselineScreenshotName ) throws IOException {

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
        Screenshot mainPageScreenshot = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+baselineScreenshotName);
        baselineScreenshots.add(mainPageScreenshot);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        driver1.navigate().refresh();
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+baselineScreenshotName);
        baselineScreenshots.add(requestPageScreenshot);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        driver1.navigate().refresh();
        registerPage.pageUp();
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshot = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+baselineScreenshotName);
        baselineScreenshots.add(registerPageScreenshot);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.pageUp();
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshot = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+baselineScreenshotName);
        baselineScreenshots.add(loginPageScreenshot);
        loginPage.login();

        log.info("Screenshot of account page");
        accountPage.pageUp();
        Screenshot accountPageDoctorScreenshot = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+baselineScreenshotName);
        baselineScreenshots.add(accountPageDoctorScreenshot);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        driver1.navigate().refresh();
        appointmentjournalPage.pageUp();;
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshot = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+baselineScreenshotName);
        baselineScreenshots.add(appointmentPageScreenshot);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        driver1.navigate().refresh();
        accessesPage.pageUp();
        Screenshot accessesPageScreenshot = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+baselineScreenshotName);
        baselineScreenshots.add(accessesPageScreenshot);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        driver1.navigate().refresh();
        brushdiaryPage.pageUp();
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshot = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushPageScreenshot);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();
///
        log.info("Screenshot of symptom page");
        driver1.navigate().refresh();
        simptomdiaryPage.pageUp();
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshot = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+baselineScreenshotName);
        baselineScreenshots.add(symptomPageScreeenshot);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        driver1.navigate().refresh();
        brushingdiaryPage.pageUp();
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshot = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushingPageScreenshot);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        driver1.navigate().refresh();
        givingaccessesPage.pageUp();
        Screenshot givingaccessesPageScreenshot = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+baselineScreenshotName);
        baselineScreenshots.add(givingaccessesPageScreenshot);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshot of teethdiary page");
        driver1.navigate().refresh();
        teethdiaryPage.pageUp();
        Screenshot teethdiaryPageScreenshot1 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot1);
        teethdiaryPage.clickOnFilterWindowButton();

        return baselineScreenshots;
    }

    @Test
    public void detectXBIonAllPagesFireFox() throws IOException, JSONException {
        Report report = new Report("All pages XBI detection");
        String reportFilesPath = "src/test/java/testdata/reports/files/";

        log.info("Google Chrome");
        driver1.get(baseUrl);
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = takeScreenshots(driver1,reportFilesPath,baselineScreenshotName);

        log.info("FireFox");
        driver2.get(baseUrl);
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = takeScreenshots(driver2,reportFilesPath,testingScreenshotName);

        log.info("MicrosoftEdge");
        driver3.get(baseUrl);
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = takeScreenshots(driver3,reportFilesPath,testingScreenshotName2);

        log.info("Compare baseline screenshots to testing screenshot");
        log.info("Compare with screenshot from FireFox");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare();
        /////////////////////////
        log.info("Compare with screenshot from MicrosoftEdge");
        Comparison comparison2 = new Comparison("GoogleChrome", "MicrosoftEdge");
        comparison2.setTestScreenshots(testingScreenshots2);
        comparison2.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences2 = comparison2.compare();
        /////////////////

        log.info("Detect DOM differences (from FireFox)");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        int j =0;
        for(VisualDifference visualDifference : visualDifferences) {
            //create report folder
            String nameOfFile = report.getNumber()+"_FF_cbd"+j+".png";
            String pageTitle = visualDifference.getTestScreenshot().getPageName();
            String path = reportFilesPath+pageTitle+"/cbd/"+nameOfFile;
            DOMdifference domDiff = visualDifference.detectDOMDifference(path);
            doMdifferences.add(domDiff);
            j++;
        }
        ///////////////
        log.info("Detect DOM differences (from MicrosoftEdge)");
        List <DOMdifference> doMdifferences2 = new ArrayList<>();
        j =0;
        for(VisualDifference visualDifference : visualDifferences2) {
            //create report folder
            String nameOfFile = report.getNumber()+"_ME_cbd"+j+".png";
            String pageTitle = visualDifference.getTestScreenshot().getPageName();
            String path = reportFilesPath+pageTitle+"/cbd/"+nameOfFile;
            DOMdifference domDiff = visualDifference.detectDOMDifference(path);
            doMdifferences2.add(domDiff);
            j++;
        }
        //////////////
        log.info("Detect incompatibilities (From FireFox)");
        List <Incompatibility> incompatibilities = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities.addAll(i);
        }
        /////////////
        log.info("Detect incompatibilities (From MicrosoftEdge)");
        List <Incompatibility> incompatibilities2 = new ArrayList<>();
        for(DOMdifference domDifference : doMdifferences2) {
            List <Incompatibility> i = domDifference.detectXBI();
            incompatibilities2.addAll(i);
        }
        ///////////
        log.info("create report");
        report.addIncompatibilities(incompatibilities);
        report.addIncompatibilities(incompatibilities2);
        report.createReport();
        report.saveReport();
        report.showReport();
    }
}
