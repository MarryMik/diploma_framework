package org.example.steps;

public class LoginSteps extends BaseSteps {

    public LoginSteps() {}

    public void loginAsDoctor() {
        mainPage.openLoginPage();
        loginPage.InputDataAsDoctor();
        loginPage.login();
    }
    public void loginAsPatient() {
        mainPage.openLoginPage();
        loginPage.InputDataAsPatient();
        loginPage.login();
    }

}
