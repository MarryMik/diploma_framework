package org.example.pages;

import org.apache.log4j.Logger;
import org.example.models.DoctorInfo;
import org.example.models.UserInfo;
import org.example.utils.IUserInfoReader;
import org.example.utils.JsonFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RequestPage extends BasePage{

    private final String requestNameInputId = "request_name_input";
    private final String requestPhoneInputId = "request_phone_input";
    private final String requestDoctorNameInputId = "request_doctor_name_input";
    private final String requestDoctorPhoneInputId = "request_doctor_phone_input";
    private final String requestDoctorAddressInputId = "request_doctor_address_input";
    private final String requestDoctorSpecializationInputId = "request_doctor_specialization_input";
    private final String requestSearchDoctorButtonId = "request_search_doctor_button";
    private final String requestChooseDoctorButtonId = "request_choose_doctor_button";
    private final String registerPageButtonId ="register_button_link";

    IUserInfoReader fileReader = new JsonFileReader();
    UserInfo userPatientInfo = fileReader.readUserInfo("src/test/java/testdata/jsonfiles/userPatientTestData.json");
    DoctorInfo userDoctorInfo = fileReader.readDoctorInfo("src/test/java/testdata/jsonfiles/userDoctorTestData.json");
    private static final Logger log = Logger.getLogger(RequestPage.class);

    //IProductInfoReader fileReader = new JsonFileReader();
    //ProductInfo productInfo = fileReader.readProductInfo("src/test/java/testdata/productTestData.json");

    public RequestPage() {}

    public void fillRequestInputsAndSearchDoctors() {
        log.info("userName"+userPatientInfo.getUserName());
        log.info("userPhone"+userPatientInfo.getUserPhone());
        log.info("doctorName"+userPatientInfo.getUserName());
        log.info("doctorPhone"+userDoctorInfo.getUserPhone());
        log.info("doctorAddress"+userDoctorInfo.getAddress());
        driver.findElement(By.id(requestNameInputId)).sendKeys(userPatientInfo.getUserName());
        driver.findElement(By.id(requestPhoneInputId)).sendKeys(userPatientInfo.getUserPhone());
        driver.findElement(By.id(requestDoctorNameInputId)).sendKeys(userDoctorInfo.getUserName());
        driver.findElement(By.id(requestDoctorPhoneInputId)).sendKeys(userDoctorInfo.getUserPhone());
        driver.findElement(By.id(requestDoctorAddressInputId)).sendKeys(userDoctorInfo.getAddress());
        driver.findElement(By.id(requestDoctorSpecializationInputId)).sendKeys(userDoctorInfo.getSpeciality());
        driver.findElement(By.id(requestSearchDoctorButtonId)).click();
    }

    public void clearAllInputs() {
        driver.findElement(By.id(requestNameInputId)).clear();
        driver.findElement(By.id(requestPhoneInputId)).clear();
        driver.findElement(By.id(requestDoctorNameInputId)).clear();
        driver.findElement(By.id(requestDoctorPhoneInputId)).clear();
        driver.findElement(By.id(requestDoctorAddressInputId)).clear();
        driver.findElement(By.id(requestDoctorSpecializationInputId)).clear();
    }

    public void pressChooseDoctorButton() {
        driver.findElement(By.id(requestChooseDoctorButtonId)).click();
    }

    public void openRegisterPage(){
        driver.findElement(By.id(registerPageButtonId)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/register"));
    }
}
