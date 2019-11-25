package com.webace.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
	 private static WebDriver driver;


	    private Driver(){

	    }

	    public static WebDriver getDriver(){
	        if(driver==null){
	            String browser = ConfigurationReader.getProperty("browser");
	            switch (browser){
	                case "chrome":
	                    WebDriverManager.chromedriver().setup();
	                    ChromeOptions options = new ChromeOptions();
	                    options.addArguments("enable-automation");
	                    //options.addArguments("--headless");
	                    options.addArguments("--window-size=1920,1080");
	                    options.addArguments("--no-sandbox");
	                    options.addArguments("--disable-extensions");
	                    options.addArguments("--dns-prefetch-disable");
	                    options.addArguments("--disable-gpu");
	                   // options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	                    driver = new ChromeDriver(options);
	                    
	                    break;
	                case "chrome-headless":
	                    WebDriverManager.chromedriver().setup();
	                  //  driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
	                    break;

	                case "firefox":
	                    WebDriverManager.firefoxdriver().setup();
	                    driver = new FirefoxDriver();
	                    break;
	                case "firefox-headless":
	                    WebDriverManager.firefoxdriver().setup();
	                  //  driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
	                    break;

	                case "ie":
	                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	                        throw new WebDriverException("Your operating system does not support the requested browser");
	                    }
	                    WebDriverManager.iedriver().setup();
	                    driver = new InternetExplorerDriver();
	                    break;

	                case "edge":
	                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	                        throw new WebDriverException("Your operating system does not support the requested browser");
	                    }
	                    WebDriverManager.edgedriver().setup();
	                    driver = new EdgeDriver();
	                    break;

	                case "safari":
	                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	                        throw new WebDriverException("Your operating system does not support the requested browser");
	                    }
	                    WebDriverManager.getInstance(SafariDriver.class).setup();
	                    driver = new SafariDriver();
	                    break;
	            }
	        }

	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	        return driver;
	    }


	
	    public static void closeDriver(){
	        if(driver!=null){
	            driver.quit();
	            driver = null;
	        }
	    }
	}

	


