package org.example.pages;

import org.example.models.UserInfo;
import org.example.utils.IProductInfoReader;
import org.example.utils.JsonFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage{

    private final String nameInputId = "nameInput";
    private final String emailInputId = "emailInput";
    private final String phoneInputId = "phoneInput";
    private final String birthDateInputId = "birthDateInput";
    private final String genderSelectId = "genderSelect";
    private final String usertypeSelectId = "usertypeSelect";
    private final String doctorOptionId = "doctorOption";
    private final String patientOptionId = "patientOption";
    private final String addressInputId = "addressInput";
    private final String specialityInputId = "specialityInput";
    private final String passwordInputId = "passwordInput";
    private final String confirmPasswordInputId = "confirmPasswordInput";
    private final String createButtonId = "createButton";
    private final String loginPageButtonId ="login_button_link";

    IProductInfoReader fileReader = new JsonFileReader();
    UserInfo userPatientInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userPatientTestData.json");
    UserInfo userDoctorInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userDoctorTestData.json");


    public RegisterPage() {}

    public void fillInputsAsPatient(){
        driver.findElement(By.id(nameInputId)).sendKeys(userPatientInfo.getUserName());
        driver.findElement(By.id(emailInputId)).sendKeys(userPatientInfo.getEmail());
        driver.findElement(By.id(phoneInputId)).sendKeys(userPatientInfo.getUserPhone());
        WebElement dataInput = driver.findElement(By.id(birthDateInputId));
        dataInput.clear();
        dataInput.sendKeys(userPatientInfo.getBirthDate());
        driver.findElement(By.id(patientOptionId)).click();
        driver.findElement(By.id(passwordInputId)).sendKeys(userPatientInfo.getPassword());
        driver.findElement(By.id(confirmPasswordInputId)).sendKeys(userPatientInfo.getPassword());
    }

    public void fillInputsAsDoctor(){
        driver.findElement(By.id(nameInputId)).sendKeys(userDoctorInfo.getUserName());
        driver.findElement(By.id(emailInputId)).sendKeys(userDoctorInfo.getEmail());
        driver.findElement(By.id(phoneInputId)).sendKeys(userDoctorInfo.getUserPhone());
        WebElement dataInput = driver.findElement(By.id(birthDateInputId));
        dataInput.clear();
        dataInput.sendKeys(userDoctorInfo.getBirthDate());
        driver.findElement(By.id(doctorOptionId)).click();
        driver.findElement(By.id(addressInputId)).sendKeys(userDoctorInfo.getAddress());
        driver.findElement(By.id(specialityInputId)).sendKeys(userDoctorInfo.getSpeciality());
        driver.findElement(By.id(passwordInputId)).sendKeys(userDoctorInfo.getPassword());
        driver.findElement(By.id(confirmPasswordInputId)).sendKeys(userDoctorInfo.getPassword());
    }

    public void clearAllInputs(){
        driver.findElement(By.id(nameInputId)).clear();
        driver.findElement(By.id(emailInputId)).clear();
        driver.findElement(By.id(phoneInputId)).clear();
        driver.findElement(By.id(birthDateInputId)).clear();
        driver.findElement(By.id(addressInputId)).clear();
        driver.findElement(By.id(specialityInputId)).clear();
        driver.findElement(By.id(passwordInputId)).clear();
        driver.findElement(By.id(confirmPasswordInputId)).clear();
    }

    public void pressRegisterButton(){
        driver.findElement(By.id(createButtonId)).click();
    }

    public void openLoginPage(){
        driver.findElement(By.id(loginPageButtonId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/login"));
    }

}
