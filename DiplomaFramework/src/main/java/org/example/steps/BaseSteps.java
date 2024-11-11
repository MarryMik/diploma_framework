package org.example.steps;
import org.example.pages.*;

public class BaseSteps {
    protected AccessesPage accessesPage;
    protected AccountPage accountPage;
    protected AppointmentjournalPage appointmentjournalPage;
    protected BrushdiaryPage brushdiaryPage;
    protected BrushingdiaryPage brushingdiaryPage;
    protected GivingaccessesPage givingaccessesPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ProcedurePage procedurePage;
    protected RecordPage recordPage;
    protected RegisterPage registerPage;
    protected RequestPage requestPage;
    protected SimptomdiaryPage simptomdiaryPage;
    protected TeethdiaryPage teethdiaryPage;
    protected TreatmentPage treatmentPage;

    public BaseSteps() {
        accessesPage = new AccessesPage();
        accountPage = new AccountPage();
        appointmentjournalPage = new AppointmentjournalPage();
        brushdiaryPage = new BrushdiaryPage();
        brushingdiaryPage = new BrushingdiaryPage();
        givingaccessesPage = new GivingaccessesPage();
        loginPage = new LoginPage();
        mainPage = new MainPage();
        procedurePage = new ProcedurePage();
        recordPage = new RecordPage();
        registerPage = new RegisterPage();
        requestPage = new RequestPage();
        simptomdiaryPage = new SimptomdiaryPage();
        teethdiaryPage = new TeethdiaryPage();
        treatmentPage = new TreatmentPage();
    }

}
