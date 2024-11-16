package org.example.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    private final String loginInputId = "loginInput";
    private final String passwordInputId = "passwordInput";
    private final String loginButtonId = "loginButton";

    public LoginPage() {}

    public void fillInputs(String login, String password) {
        driver.findElement(By.id(loginInputId)).sendKeys(login);
        driver.findElement(By.id(passwordInputId)).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(By.id(loginButtonId)).click();
    }
}
