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

    // @Test(priority = 2)
    // public void Valid_Login_TC() throws IOException {
    //     try {
    //         ExtentTest test = reporter.createTest("Basic log", "Execution for Login Function");
    //         e = new EventHandler();
    //         driver.get(prop.getProperty("url") + "/Login");
    //         driver.findElement(By.name("username")).sendKeys("S@gmail.com");
    //         log.info("User name sent");
    //         driver.findElement(By.id("password")).sendKeys("P@ssword12");
    //         log.info("Password Sent");
    //         driver.findElement(By.id("submit")).click();
    //         log.info("Submit Button clicked");

    //     } 
    //     catch (Exception ex) {
    //         // Handle any exceptions here
    //         log.info("Exception occurred: " + ex.getMessage());
    //         Exception_Screenshot screenshotHandler = new Exception_Screenshot();
    //         screenshotHandler.captureScreenshot(driver, "Login_Exception");
    //         ex.printStackTrace();
    //     }
    // }
//     @Test(priority=3, dataProvider="testData")
//   public void WithdrawTest(String amt) throws IOException {
// 	try {
//         ExtentTest test = reporter.createTest("Withdraw Test", "Execution for withdraw operation");
//         e = new EventHandler();
//         driver.get(prop.getProperty("url") + "/login");
//         // e.logger("Browser Navigated to the Login Page");
//         driver.findElement(By.id("username")).sendKeys("S@gmail.com");
//         log.info("User name sent");
//         driver.findElement(By.id("password")).sendKeys("P@ssword12");
//         log.info("Password Sent");
//         driver.findElement(By.id("submit")).click();
//         log.info("Submit Button clicked");
//         driver.findElement(By.linkText("Withdraw")).click();
//         log.info("Withdraw Button click");
//         Select accType = new Select(driver.findElement(By.id("selectedAccount")));
//         log.info("Account type has been Selected");
//         accType.selectByVisibleText("Individual Checking (Standard Checking)");
//         driver.findElement(By.id("amount")).sendKeys(amt);
//         driver.findElement(By.xpath("//button[text()=' Submit']")).click();

      
//                 test.pass("Withdrawal completed successfully");
//     }
// catch(Exception ex){
//     Exception_Screenshot screenshotHandler = new Exception_Screenshot();
//     screenshotHandler.captureScreenshot(driver, "Vote_without_Login_Exception");
// }

// }
 @DataProvider(name = "testData")
// public Object[][] readTestData() throws IOException {
//     String excelFilePath = System.getProperty("user.dir") + "/src/test/java/resources/Testdata.xlsx";
//     String sheetName = "Sheet1";
//     int columnToRead = 2;

//     FileInputStream fileInputStream = new FileInputStream(excelFilePath);
//     Workbook workbook = WorkbookFactory.create(fileInputStream);
//     Sheet sheet = workbook.getSheet(sheetName);

//     int rowCount = sheet.getLastRowNum();
//     int colCount = sheet.getRow(0).getLastCellNum();

//     Object[][] data = new Object[rowCount][1];
//     for (int i = 1; i <= rowCount; i++) {
//         Row row = sheet.getRow(i);
//         Cell cell = row.getCell(columnToRead, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//         data[i - 1][0] = cell.getStringCellValue();
//     }


//     return data;
// }

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
