package org.example.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage{

    private final String nameInputId = "nameInput";
    private final String emailInputId = "emailInput";
    private final String phoneInputId = "phoneInput";
    private final String birthDateInputId = "birthDateInput";
    private final String genderSelectId = "genderSelect";
    private final String usertypeSelectId = "usertypeSelect";
    private final String doctorOptionId = "doctorOption";
    private final String addressInputId = "addressInput";
    private final String specialityInputId = "specialityInput";
    private final String passwordInputId = "passwordInput";
    private final String confirmPasswordInputId = "confirmPasswordInput";
    private final String createButtonId = "createButton";

    public RegisterPage() {}

    public void fillGeneralInputs(){

    }

    public void fillDoctorInputs(){

    }

    public void pressRegisterButton(){
        driver.findElement(By.id(createButtonId)).click();
    }

}
