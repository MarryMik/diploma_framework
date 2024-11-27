import org.apache.log4j.Logger;
import org.example.xbidetection.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GCandFFDetectionTests extends BaseTest{
    private static final Logger log = Logger.getLogger(GCandFFDetectionTests.class);

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
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshot = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+baselineScreenshotName);
        baselineScreenshots.add(requestPageScreenshot);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshot = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+baselineScreenshotName);
        baselineScreenshots.add(registerPageScreenshot);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshot = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+baselineScreenshotName);
        baselineScreenshots.add(loginPageScreenshot);
        loginPage.login();

        log.info("Screenshot of account page");
        Screenshot accountPageDoctorScreenshot = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+baselineScreenshotName);
        baselineScreenshots.add(accountPageDoctorScreenshot);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshot = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+baselineScreenshotName);
        baselineScreenshots.add(appointmentPageScreenshot);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        Screenshot accessesPageScreenshot = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+baselineScreenshotName);
        baselineScreenshots.add(accessesPageScreenshot);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshot = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushPageScreenshot);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();

        log.info("Screenshot of symptom page");
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshot = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+baselineScreenshotName);
        baselineScreenshots.add(symptomPageScreeenshot);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshot = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+baselineScreenshotName);
        baselineScreenshots.add(brushingPageScreenshot);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        Screenshot givingaccessesPageScreenshot = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+baselineScreenshotName);
        baselineScreenshots.add(givingaccessesPageScreenshot);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshots of teethdiary page");
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
        //close filter ///

        teethdiaryPage.openShareWithDoctorWindow();
        Screenshot teethdiaryPageScreenshot5 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/share_window/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot5);

        teethdiaryPage.closeOpenShareWithDoctorWindow();
        teethdiaryPage.filterRecords();
        Screenshot teethdiaryPageScreenshot6 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_records/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot6);

        teethdiaryPage.filterProcedures();
        Screenshot teethdiaryPageScreenshot7 = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_procedures/"+baselineScreenshotName);
        baselineScreenshots.add(teethdiaryPageScreenshot7);

        teethdiaryPage.filterTreatments();
        teethdiaryPage.openTreatmentEditPage();

        log.info("Screenshot of treatment page");
        Screenshot treatmentPageScreenshot = treatmentPage.takeScreenshot(reportFilesPath+"treatmentpage/"+baselineScreenshotName);
        baselineScreenshots.add(treatmentPageScreenshot);
        treatmentPage.openRecordEditPage();

        log.info("Screenshot of record page");
        Screenshot recordPageScreenshot = recordPage.takeScreenshot(reportFilesPath+"recordpage/"+baselineScreenshotName);
        baselineScreenshots.add(recordPageScreenshot);
        recordPage.openProcedureEditPage();

        log.info("Screenshot of procedures page");
        Screenshot procedurePageScreenshot = procedurePage.takeScreenshot(reportFilesPath+"procedurepage/"+baselineScreenshotName);
        baselineScreenshots.add(procedurePageScreenshot);
        procedurePage.openTeethDiaryPage();
        teethdiaryPage.openProcedureFilePage();

        log.info("Screenshot of file page");
        Screenshot filePageScreenshot = teethdiaryPage.takeScreenshot(reportFilesPath+"filepage/"+baselineScreenshotName);
        baselineScreenshots.add(filePageScreenshot);


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
        Screenshot mainPageScreenshotT = mainPage.takeScreenshot(reportFilesPath+"mainpage/"+testingScreenshotName);
        testingScreenshots.add(mainPageScreenshotT);
        mainPage.openRequestPage();

        log.info("Screenshot of request page");
        requestPage.fillRequestInputsAndSearchDoctors();
        Screenshot requestPageScreenshotT = requestPage.takeScreenshot(reportFilesPath+"requestpage/"+testingScreenshotName);
        testingScreenshots.add(requestPageScreenshotT);
        requestPage.openRegisterPage();

        log.info("Screenshot of reqister page");
        registerPage.fillInputsAsDoctor();
        Screenshot registerPageScreenshotT = registerPage.takeScreenshot(reportFilesPath+"registerpage/"+testingScreenshotName);
        testingScreenshots.add(registerPageScreenshotT);
        registerPage.openLoginPage();

        log.info("Screenshot of login page");
        loginPage.InputDataAsDoctor();
        Screenshot loginPageScreenshotT = loginPage.takeScreenshot(reportFilesPath+"loginpage/"+testingScreenshotName);
        testingScreenshots.add(loginPageScreenshotT);
        loginPage.login();

        log.info("Screenshot of account page");
        Screenshot accountPageDoctorScreenshotT = accountPage.takeScreenshot(reportFilesPath+"accountpage/"+testingScreenshotName);
        testingScreenshots.add(accountPageDoctorScreenshotT);
        accountPage.openAppointmentPage();

        log.info("Screenshot of appointment page");
        appointmentjournalPage.openAppointWindow();
        Screenshot appointmentPageScreenshotT = appointmentjournalPage.takeScreenshot(reportFilesPath+"appointmentpage/"+testingScreenshotName);
        testingScreenshots.add(appointmentPageScreenshotT);
        appointmentjournalPage.openAccessesPage();

        log.info("Screenshot of accesses page");
        Screenshot accessesPageScreenshotT = accessesPage.takeScreenshot(reportFilesPath+"accessespage/"+testingScreenshotName);
        testingScreenshots.add(accessesPageScreenshotT);
        accessesPage.logout();

        log.info("Screenshot of login as patient");
        loginSteps.loginAsPatient();
        accountPage.openBrushesPage();

        log.info("Screenshot of brushes page");
        brushdiaryPage.openNewBrushWindow();
        Screenshot brushPageScreenshotT = brushdiaryPage.takeScreenshot(reportFilesPath+"brushpage/"+testingScreenshotName);
        testingScreenshots.add(brushPageScreenshotT);
        brushdiaryPage.closeBrushWindoe();
        brushdiaryPage.openSymptomsPage();

        log.info("Screenshot of symptom page");
        simptomdiaryPage.openSymptomWindow();
        Screenshot symptomPageScreeenshotT = simptomdiaryPage.takeScreenshot(reportFilesPath+"symptompage/"+testingScreenshotName);
        testingScreenshots.add(symptomPageScreeenshotT);
        simptomdiaryPage.closeSymptomWindow();
        simptomdiaryPage.openBrushingPage();

        log.info("Screenshot of brushing page");
        brushingdiaryPage.clickOnCheckbox();
        Screenshot brushingPageScreenshotT = brushingdiaryPage.takeScreenshot(reportFilesPath+"brushingpage/"+testingScreenshotName);
        testingScreenshots.add(brushingPageScreenshotT);
        brushingdiaryPage.openGivingAccessPage();

        log.info("Screenshot of giving access page");
        Screenshot givingaccessesPageScreenshotT = givingaccessesPage.takeScreenshot(reportFilesPath+"givingaccesses/"+testingScreenshotName);
        testingScreenshots.add(givingaccessesPageScreenshotT);
        givingaccessesPage.openTeethDiaryPage();

        log.info("Screenshots of teethdiary page");
        Screenshot teethdiaryPageScreenshot1T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/page/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot1T);
        teethdiaryPage.clickOnFilterWindowButton();

        Screenshot teethdiaryPageScreenshot2T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_window/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot2T);

        teethdiaryPage.openFilterDataInput();
        Screenshot teethdiaryPageScreenshot3T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_date_input/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot3);

        teethdiaryPage.closeFilterDataInput();
        teethdiaryPage.clickOnFilterSelectInput();
        Screenshot teethdiaryPageScreenshot4T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_select_input/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot4T);
        //close filter ///

        teethdiaryPage.openShareWithDoctorWindow();
        Screenshot teethdiaryPageScreenshot5T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/share_window/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot5T);

        teethdiaryPage.closeOpenShareWithDoctorWindow();
        teethdiaryPage.filterRecords();
        Screenshot teethdiaryPageScreenshot6T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_records/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot6T);

        teethdiaryPage.filterProcedures();
        Screenshot teethdiaryPageScreenshot7T = teethdiaryPage.takeScreenshot(reportFilesPath+"teethdiary/filter_procedures/"+testingScreenshotName);
        testingScreenshots.add(teethdiaryPageScreenshot7T);

        teethdiaryPage.filterTreatments();
        teethdiaryPage.openTreatmentEditPage();

        log.info("Screenshot of treatment page");
        Screenshot treatmentPageScreenshotT = treatmentPage.takeScreenshot(reportFilesPath+"treatmentpage/"+testingScreenshotName);
        testingScreenshots.add(treatmentPageScreenshotT);
        treatmentPage.openRecordEditPage();

        log.info("Screenshot of record page");
        Screenshot recordPageScreenshotT = recordPage.takeScreenshot(reportFilesPath+"recordpage/"+testingScreenshotName);
        testingScreenshots.add(recordPageScreenshotT);
        recordPage.openProcedureEditPage();

        log.info("Screenshot of procedures page");
        Screenshot procedurePageScreenshotT = procedurePage.takeScreenshot(reportFilesPath+"procedurepage/"+testingScreenshotName);
        testingScreenshots.add(procedurePageScreenshotT);
        procedurePage.openTeethDiaryPage();
        teethdiaryPage.openProcedureFilePage();

        log.info("Screenshot of file page");
        Screenshot filePageScreenshotT = teethdiaryPage.takeScreenshot(reportFilesPath+"filepage/"+testingScreenshotName);
        testingScreenshots.add(filePageScreenshotT);

        log.info("Compare baseline screenshots to testing screenshot");
        Comparison comparison = new Comparison("GoogleChrome", "FireFox");
        comparison.setTestScreenshots(testingScreenshots);
        comparison.setBaselineScreenshots(baselineScreenshots);
        List <VisualDifference> visualDifferences = comparison.compare();

        log.info("Detect DOM differences");
        List <DOMdifference> doMdifferences = new ArrayList<>();
        for(VisualDifference visualDifference : visualDifferences) {
            String nameOfFile = report.getNumber()+"_FF_drawing.png";
            String pageTitle = visualDifference.getTestScreenshot().getPageName();
            String path = reportFilesPath+pageTitle+"/"+nameOfFile;
            DOMdifference domDiff = visualDifference.detectDOMDifference(path);
            doMdifferences.add(domDiff);
        }
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
