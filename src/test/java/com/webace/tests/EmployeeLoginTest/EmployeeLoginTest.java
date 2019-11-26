package com.webace.tests.EmployeeLoginTest;

import com.github.javafaker.Faker;
import com.webace.utilities.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.webace.utilities.Driver;
import com.webace.utilities.TestBase;
import com.webace.utilities.WebAceUtils;
import static  com.webace.utilities.ApplicationConstants.LOGIN_ERROR_MESSAGE;

public class EmployeeLoginTest extends TestBase {
	WebDriver driver = Driver.getDriver();
	Faker faker = new Faker();


    @Test(priority = 0)
    public void loginTest1(){
         extentLogger = report.createTest("Login as a webace employee");
        //this is required, otherwise you will get null pointer exception
        extentLogger.info("Entering username in username field");
        pages.empLogin().login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        //we are instantiating page class inside a Page class and creating a method to initiate it, then we are calling it in each test method
        //because for second test, if we run all tests in a row, driver will have null session
        extentLogger.pass("Successfuly logged in to webace application");
        Reporter.getCurrentTestResult();
        driver.close();
    }
  @Test(priority=1)
    public void negativeLoginTest1(){
        extentLogger = report.createTest("Login with invalid credentials");
        String invalid_login_message = pages.empLogin().invalid_login_message.getText();
        System.out.println(invalid_login_message);
        extentLogger.info("Logging with username: wrongusername, and password: wrongpassword");
        pages.empLogin().login(faker.name().firstName(), faker.funnyName().name());
         assertEquals( invalid_login_message, LOGIN_ERROR_MESSAGE);
         extentLogger.pass("Verified that warning message displayed: Invalid login. Please try again.");
}}
