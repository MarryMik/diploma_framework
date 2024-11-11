import org.example.core.*;
import org.example.pages.*;
import org.example.steps.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import static org.example.core.WebDriverInstance.driverInstance;

public class BaseTest {
    protected WebDriver driver1;
    protected WebDriver driver2;
    protected WebDriver driver3;
    protected final String baseUrl = "http://localhost:3000/";

    //pages
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

    //steps
    protected LoginSteps loginSteps;

    @BeforeEach
    public void setUp() {
        driver1 = WebDriverFactory.getWebDriver("chrome");
        driverInstance().setDriver1(driver1);
        driver2 = WebDriverFactory.getWebDriver("firefox");
        driverInstance().setDriver2(driver2);
        driver3 = WebDriverFactory.getWebDriver("edge");
        driverInstance().setDriver3(driver3);

        //init pages
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

        // init steps
        loginSteps = new LoginSteps();
    }
    @AfterEach
    public void tearDown() {
        if(driver1 != null){
            driver1.quit();
        }
        if(driver2 != null){
            driver2.quit();
        }
        if(driver3 != null){
            driver3.quit();
        }
    }
}
