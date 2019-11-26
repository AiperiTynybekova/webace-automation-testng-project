package com.webace.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sun.webkit.ThemeClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
	
	 //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;
    public Pages pages;

    //we need this object for building reports, but it doesn't build itself
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void testSetup( ){
        report = new ExtentReports();
        //we are creating actual reporter
        //this is our custom location of the report that will be generated
        //report will be generated in the current project inside folder : target
        //report file name : report.html
        String pathToReport = System.getProperty("user.dir")+"/target/report.html";
        //this is path to the report itself
        htmlReporter = new ExtentHtmlReporter(pathToReport);
        //initialize the htmlReporter with the path to the report

        report.attachReporter(htmlReporter);
        //we attach the htmlreport to our report
        report.setSystemInfo("OS", "WINDOWS");
        report.setSystemInfo("Environment", "hotfix");
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("QA Engineer", "Aiperi Tynybekova");
        //we can add system information to report

        htmlReporter.config().setDocumentTitle("WebAce Reports");
        htmlReporter.config().setReportName("WebAce Automated Test Reports");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        //we can add the above config info

    }

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        pages = new Pages();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("rewrite"));
    }


    //ITestresult describes the result of a test.
    //we can determine if test failed, passed or ignored
    @AfterMethod
    public void teardown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()) {
           //if test failed get a screenshot and save the location to the image
            String pathToTheScreenshot = BrowserUtils.getScreenshot(result.getName());

            // capture the name of a test method that failed
            extentLogger.fail(result.getName());
            try {
                //to add screenshot into report
                extentLogger.addScreenCaptureFromPath(pathToTheScreenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //to add thrown exception into report
            extentLogger.fail(result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            //if test skipped, this information will appear on the report
            extentLogger.skip("Test case skipped "+result.getName());
        }
        Driver.closeDriver();
    }

    @AfterTest
    public void tearDownTest(){

        report.flush();
    }
}



