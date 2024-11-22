package org.example.pages;

import org.example.models.UserInfo;
import org.example.utils.IUserInfoReader;
import org.example.utils.JsonFileReader;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    private final String loginInputId = "loginInput";
    private final String passwordInputId = "passwordInput";
    private final String loginButtonId = "loginButton";

    IUserInfoReader fileReader = new JsonFileReader();
    UserInfo userPatientInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userPatientTestData.json");
    UserInfo userDoctorInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userDoctorTestData.json");

    public LoginPage() {}

    public void InputDataAsDoctor() {
        driver.findElement(By.id(loginInputId)).sendKeys(userDoctorInfo.getEmail());
        driver.findElement(By.id(passwordInputId)).sendKeys(userDoctorInfo.getPassword());
    }

    public void InputDataAsPatient() {
        driver.findElement(By.id(loginInputId)).sendKeys(userPatientInfo.getEmail());
        driver.findElement(By.id(passwordInputId)).sendKeys(userPatientInfo.getPassword());
    }

    public void login(){
        driver.findElement(By.id(loginButtonId)).click();
    }
}
