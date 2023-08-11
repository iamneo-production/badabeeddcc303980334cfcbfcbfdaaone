package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import utils.Exception_Screenshot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.LoggerHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;


import utils.EventHandler;
import utils.Reporter;
// import utils.Screenshot;
public class Testcase1 extends Base {
    EventHandler e;
    ExtentSparkReporter sparkReporter;
    ExtentReports reporter = Reporter.generateExtentReport();
    java.util.logging.Logger log =  LoggerHandler.getLogger();
    
    @Test(priority = 1, dataProvider="testData")
    public void Register(String amt) throws IOException {
        try {
            ExtentTest test = reporter.createTest("Registeration Page", "Execution for registeration");
            e = new EventHandler();
            driver.get(prop.getProperty("url") + "/login");
            driver.manage().window().maximize();
            log.info("Browser Launched");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.name("username")).sendKeys("S@gmail.com");
            log.info("Mail ID Sent");
            driver.findElement(By.id("password")).sendKeys("P@ssword12");
            log.info("Password Sent");
            driver.findElement(By.id("submit")).click();
            log.info("Submit Button clicked");
            Duration timeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            WebElement depositLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Deposit")));
            depositLink.click();
	    Select accType=new Select(driver.findElement(By.id("selectedAccount")));
        log.info("Account Type has been selected");
	    accType.selectByVisibleText("Individual Checking (Standard Checking)");
        driver.findElement(By.id("amount")).sendKeys(amt);
        log.info("Amount has been sent");
	    driver.findElement(By.xpath("//button[text()=' Submit']")).click();
        
        } catch (Exception ex) {
            Exception_Screenshot screenshotHandler = new Exception_Screenshot();
            screenshotHandler.captureScreenshot(driver, "Registeration_Exception");
            log.info("Exception occurred: " + ex.getMessage());
            
            ex.printStackTrace();
        }
        WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return;
    }

    @DataProvider(name = "testData")
public Object[][] readTestData() throws IOException {
    
    return null;
    }





    

@BeforeMethod
public void beforeMethod() throws MalformedURLException {
    openBrowser();

}

    @AfterMethod
    public void afterMethod() {
        driver.quit();
        reporter.flush();
        log.info("Browser closed");
        LoggerHandler.closeHandler();
    }
}
