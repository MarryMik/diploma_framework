package org.example.pages;

import org.example.models.DoctorInfo;
import org.example.models.UserInfo;
import org.example.utils.IUserInfoReader;
import org.example.utils.JsonFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Utils.sleep;

public class TeethdiaryPage extends BasePage{

    private final String treatmentEditButtonXpath="//div[@class=\"treatment-card__buttns_wrap\"]/button[@class=\"buttn_edit\"]";
    private final String treatmentsFilterButtonId = "treatments_filter_button";
    private final String recordsFilterButtonId = "records_filter_button";
    private final String proceduresFilterButtonId = "procedures_filter_button";
    private final String filterButtonXpath = "//button[@class=\"button__filter\"]";
    private final String filterDateInputXpath = "//div[@class=\"filter-board__date-wrap\"]/input[@type=\"date\"]";
    private final String filterSelectProceduresInputXpath = "//p[@class=\"filter-board__text_title\" and text()=\"Процедура:\"]/following-sibling::div/div";
    private final String shareButtonXpath ="//p[@class=\"nav-panel__button_text\" and text()=\"Поділитися\"]";
    private final String searchDoctorNameInputXpath="//input[@class=\"sharing-board__input_name\" and @name=\"username\"]";
    private final String searchDoctorPhoneInputXpath="//input[@class=\"sharing-board__input_name\" and @name=\"phone\"]";
    private final String searchDoctorButtonXpath ="//button[@class=\"sharing-board__buttn_search\"]";
    private final String procedureFilePageButtonXpath ="//div[@class=\"procedure-card\"]/button[@class=\"procedure-card__file-img\"]";


    IUserInfoReader fileReader = new JsonFileReader();
    DoctorInfo userDoctorInfo = fileReader.readDoctorInfo("src/test/java/testdata/jsonfiles/userDoctorTestData.json");

    public TeethdiaryPage() {}

    public void filterTreatments(){
        driver.findElement(By.id(this.treatmentsFilterButtonId)).click();
        sleep(1000);
    }

    public void filterRecords(){
        driver.findElement(By.id(this.recordsFilterButtonId)).click();
        sleep(1000);
    }

    public void filterProcedures(){
        driver.findElement(By.id(this.proceduresFilterButtonId)).click();
        sleep(1000);
    }

    public void openTreatmentEditPage(){
        driver.findElement(By.xpath(this.treatmentEditButtonXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/treatment"));
    }

    public void clickOnFilterWindowButton (){
        driver.findElement(By.xpath(this.filterButtonXpath)).click();
    }

    public void openFilterDataInput(){
        WebElement filterDateInput = driver.findElement(By.xpath(this.filterDateInputXpath));
        int xOffset = 170;
        int yOffset = 17;
        Actions actions = new Actions(driver);
        actions.moveToElement(filterDateInput).moveByOffset(xOffset, yOffset).click().perform();
    }

    public void closeFilterDataInput(){
        driver.findElement(By.xpath(this.filterDateInputXpath)).click();
        sleep(1000);
    }

    public void clickOnFilterSelectInput(){
        driver.findElement(By.xpath(this.filterSelectProceduresInputXpath)).click();
    }

    public void openShareWithDoctorWindow(){
        driver.findElement(By.xpath(this.shareButtonXpath)).click();
        sleep(1000);
        driver.findElement(By.xpath(this.searchDoctorNameInputXpath)).sendKeys(userDoctorInfo.getUserName());
        driver.findElement(By.xpath(this.searchDoctorPhoneInputXpath)).sendKeys(userDoctorInfo.getUserPhone());
        driver.findElement(By.xpath(this.searchDoctorButtonXpath)).click();
        sleep(2000);
    }

    public void closeOpenShareWithDoctorWindow(){
        driver.findElement(By.xpath(this.shareButtonXpath)).click();
    }

    public void openProcedureFilePage(){
        driver.findElement(By.xpath(this.procedureFilePageButtonXpath)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/procedure_file"));
    }


}
