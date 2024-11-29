import org.apache.log4j.Logger;
import org.example.xbidetection.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Utils.sleep;


public class GeneralPagesTests extends BaseTest{
    private static final Logger log = Logger.getLogger(GeneralPagesTests.class);

    @Test
    public void detectXBIonAllPagesFireFox() throws IOException, JSONException {
        Report report = new Report("All pages XBI detection");
        String reportFilesPath ="src/test/java/testdata/reports/files/";

        log.info("Google Chrome");
        driver1.get(baseUrl);

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
        String baselineScreenshotName = report.getNumber()+"_GC_baseline.png";
        List<Screenshot> baselineScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        Screenshot mainPageScreenshot = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+baselineScreenshotName);
        baselineScreenshots.add(mainPageScreenshot);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+baselineScreenshotName);
        baselineScreenshots.add(requestPageScreenshot);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
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
        appointmentjournalPage.pageUp();;
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshot = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+baselineScreenshotName);
        baselineScreenshots.add(appointmentPageScreenshot);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        accessesPage.pageUp();
        Screenshot accessesPageScreenshot = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+baselineScreenshotName);
        baselineScreenshots.add(accessesPageScreenshot);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        brushdiaryPage.pageUp();
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshot = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushPageScreenshot);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();
///
        log.info("Screenshot of symptom page");
        simptomdiaryPage.pageUp();
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshot = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+baselineScreenshotName);
        baselineScreenshots.add(symptomPageScreeenshot);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        brushingdiaryPage.pageUp();
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshot = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushingPageScreenshot);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        givingaccessesPage.pageUp();
        Screenshot givingaccessesPageScreenshot = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+baselineScreenshotName);
        baselineScreenshots.add(givingaccessesPageScreenshot);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshots of teethdiary page");
        teethdiaryPage.pageUp();
        Screenshot teethdiaryPageScreenshot1 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/page/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot1);
        teethdiaryPage.clickOnFilterWindowButton();

        Screenshot teethdiaryPageScreenshot2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_window/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot2);

        teethdiaryPage.openFilterDataInput();
        Screenshot teethdiaryPageScreenshot3 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_date_input/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot3);

        teethdiaryPage.closeFilterDataInput();
        teethdiaryPage.clickOnFilterSelectInput();
        Screenshot teethdiaryPageScreenshot4 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_select_input/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot4);
        teethdiaryPage.pageUp();
        teethdiaryPage.clickOnFilterWindowButton();

        teethdiaryPage.pageUp();
        teethdiaryPage.openShareWithDoctorWindow();
        Screenshot teethdiaryPageScreenshot5 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/share_window/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot5);

        teethdiaryPage.pageUp();
        teethdiaryPage.closeOpenShareWithDoctorWindow();
        teethdiaryPage.filterRecords();
        Screenshot teethdiaryPageScreenshot6 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_records/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot6);

        teethdiaryPage.pageUp();
        teethdiaryPage.filterProcedures();
        Screenshot teethdiaryPageScreenshot7 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_procedures/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot7);

////////////////////////////////////////////////////////
        log.info("FireFox");
        driver2.get(baseUrl);

        log.info("set second driver to pages");
        accessesPage.setDriver(driver2);
        accountPage.setDriver(driver2);
        appointmentjournalPage.setDriver(driver2);
        brushdiaryPage.setDriver(driver2);
        brushingdiaryPage.setDriver(driver2);
        givingaccessesPage.setDriver(driver2);
        loginPage.setDriver(driver2);
        mainPage.setDriver(driver2);
        procedurePage.setDriver(driver2);
        recordPage.setDriver(driver2);
        registerPage.setDriver(driver2);
        requestPage.setDriver(driver2);
        simptomdiaryPage.setDriver(driver2);
        teethdiaryPage.setDriver(driver2);
        treatmentPage.setDriver(driver2);
        loginSteps.setDriver(driver2);

        log.info("Take testing screenshots");
        String testingScreenshotName = report.getNumber()+"_FF_testing.png";
        List<Screenshot> testingScreenshots = new ArrayList<>();

        log.info("Screenshot of main page");
        mainPage.pageUp();
        Screenshot mainPageScreenshotT = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+testingScreenshotName);
        testingScreenshots.add(mainPageScreenshotT);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshotT = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+testingScreenshotName);
        testingScreenshots.add(requestPageScreenshotT);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        registerPage.pageUp();
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshotT = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+testingScreenshotName);
        testingScreenshots.add(registerPageScreenshotT);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.pageUp();
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshotT = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+testingScreenshotName);
        testingScreenshots.add(loginPageScreenshotT);
        loginPage.login();

        log.info("Screenshot of account page");
        accountPage.pageUp();
        Screenshot accountPageDoctorScreenshotT = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+testingScreenshotName);
        testingScreenshots.add(accountPageDoctorScreenshotT);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        appointmentjournalPage.pageUp();
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshotT = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+testingScreenshotName);
        testingScreenshots.add(appointmentPageScreenshotT);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        accessesPage.pageUp();
        Screenshot accessesPageScreenshotT = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+testingScreenshotName);
        testingScreenshots.add(accessesPageScreenshotT);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        brushdiaryPage.pageUp();
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshotT = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+testingScreenshotName);
        testingScreenshots.add(brushPageScreenshotT);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();

        log.info("Screenshot of symptom page");
        simptomdiaryPage.pageUp();
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshotT = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+testingScreenshotName);
        testingScreenshots.add(symptomPageScreeenshotT);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        brushingdiaryPage.pageUp();
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshotT = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+testingScreenshotName);
        testingScreenshots.add(brushingPageScreenshotT);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        givingaccessesPage.pageUp();
        Screenshot givingaccessesPageScreenshotT = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+testingScreenshotName);
        testingScreenshots.add(givingaccessesPageScreenshotT);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshots of teethdiary page");
        teethdiaryPage.pageUp();
        Screenshot teethdiaryPageScreenshot1T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/page/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot1T);
        teethdiaryPage.clickOnFilterWindowButton();

        Screenshot teethdiaryPageScreenshot2T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_window/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot2T);

        teethdiaryPage.openFilterDataInput();
        Screenshot teethdiaryPageScreenshot3T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_date_input/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot3T);

        teethdiaryPage.closeFilterDataInput();
        teethdiaryPage.clickOnFilterSelectInput();
        Screenshot teethdiaryPageScreenshot4T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_select_input/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot4T);
        teethdiaryPage.pageUp();
        teethdiaryPage.clickOnFilterWindowButton();

        teethdiaryPage.pageUp();
        teethdiaryPage.openShareWithDoctorWindow();
        Screenshot teethdiaryPageScreenshot5T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/share_window/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot5T);

        teethdiaryPage.pageUp();
        teethdiaryPage.closeOpenShareWithDoctorWindow();
        teethdiaryPage.filterRecords();
        Screenshot teethdiaryPageScreenshot6T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_records/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot6T);

        teethdiaryPage.pageUp();
        teethdiaryPage.filterProcedures();
        Screenshot teethdiaryPageScreenshot7T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_procedures/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot7T);

        teethdiaryPage.filterTreatments();

//////////////////////////////
        log.info("MicrosoftEdge");
        driver3.get(baseUrl);

        log.info("set second driver to pages");
        accessesPage.setDriver(driver3);
        accountPage.setDriver(driver3);
        appointmentjournalPage.setDriver(driver3);
        brushdiaryPage.setDriver(driver3);
        brushingdiaryPage.setDriver(driver3);
        givingaccessesPage.setDriver(driver3);
        loginPage.setDriver(driver3);
        mainPage.setDriver(driver3);
        procedurePage.setDriver(driver3);
        recordPage.setDriver(driver3);
        registerPage.setDriver(driver3);
        requestPage.setDriver(driver3);
        simptomdiaryPage.setDriver(driver3);
        teethdiaryPage.setDriver(driver3);
        treatmentPage.setDriver(driver3);
        loginSteps.setDriver(driver3);

        log.info("Take testing screenshots");
        String testingScreenshotName2 = report.getNumber()+"_ME_testing.png";
        List<Screenshot> testingScreenshots2 = new ArrayList<>();

        log.info("Screenshot of main page");
        mainPage.pageUp();
        Screenshot mainPageScreenshotT2 = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+testingScreenshotName2);
        testingScreenshots2.add(mainPageScreenshotT2);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.pageUp();
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshotT2 = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+testingScreenshotName2);
        testingScreenshots2.add(requestPageScreenshotT2);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        registerPage.pageUp();
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshotT2 = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+testingScreenshotName2);
        testingScreenshots2.add(registerPageScreenshotT2);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.pageUp();
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshotT2 = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+testingScreenshotName2);
        testingScreenshots2.add(loginPageScreenshotT2);
        loginPage.login();

        log.info("Screenshot of account page");
        accountPage.pageUp();
        Screenshot accountPageDoctorScreenshotT2 = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+testingScreenshotName2);
        testingScreenshots2.add(accountPageDoctorScreenshotT2);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        appointmentjournalPage.pageUp();
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshotT2 = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+testingScreenshotName2);
        testingScreenshots2.add(appointmentPageScreenshotT2);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        accessesPage.pageUp();
        Screenshot accessesPageScreenshotT2 = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+testingScreenshotName2);
        testingScreenshots2.add(accessesPageScreenshotT2);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        brushdiaryPage.pageUp();
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshotT2 = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+testingScreenshotName2);
        testingScreenshots2.add(brushPageScreenshotT2);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();

        log.info("Screenshot of symptom page");
        simptomdiaryPage.pageUp();
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshotT2 = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+testingScreenshotName2);
        testingScreenshots2.add(symptomPageScreeenshotT2);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        brushdiaryPage.pageUp();
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshotT2 = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+testingScreenshotName2);
        testingScreenshots2.add(brushingPageScreenshotT2);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        givingaccessesPage.pageUp();
        Screenshot givingaccessesPageScreenshotT2 = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+testingScreenshotName2);
        testingScreenshots2.add(givingaccessesPageScreenshotT2);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshots of teethdiary page");
        teethdiaryPage.pageUp();
        Screenshot teethdiaryPageScreenshot1T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/page/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot1T2);
        teethdiaryPage.clickOnFilterWindowButton();

        Screenshot teethdiaryPageScreenshot2T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_window/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot2T2);

        teethdiaryPage.openFilterDataInput();
        Screenshot teethdiaryPageScreenshot3T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_date_input/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot3T2);

        teethdiaryPage.closeFilterDataInput();
        teethdiaryPage.clickOnFilterSelectInput();
        Screenshot teethdiaryPageScreenshot4T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_select_input/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot4T2);
        teethdiaryPage.pageUp();
        teethdiaryPage.clickOnFilterWindowButton();

        teethdiaryPage.pageUp();
        teethdiaryPage.openShareWithDoctorWindow();
        Screenshot teethdiaryPageScreenshot5T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/share_window/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot5T2);

        teethdiaryPage.pageUp();
        teethdiaryPage.closeOpenShareWithDoctorWindow();
        teethdiaryPage.filterRecords();
        Screenshot teethdiaryPageScreenshot6T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_records/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot6T2);

        teethdiaryPage.pageUp();
        teethdiaryPage.filterProcedures();
        Screenshot teethdiaryPageScreenshot7T2 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_procedures/"+testingScreenshotName2);
        testingScreenshots2.add(teethdiaryPageScreenshot7T2);

//////////////////////////////
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
