package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



// import utils.Screenshot;
public class Testcase1 extends Base {
   
    
    
    
    @Test(priority = 1, dataProvider="testData")
    public void Register(String amt) throws IOException {
        try {
            driver.get(prop.getProperty("url") + "/login");
            driver.manage().window().maximize();
        
        } catch (Exception ex) {
        
            
            ex.printStackTrace();
        }
       
    }


@DataProvider(name = "testData")
public Object[][] readTestData() throws IOException {
    String excelFilePath = System.getProperty("user.dir") + "/src/test/java/resources/Testdata.xlsx";
    String sheetName = "Sheet1";
    int columnToRead = 2;

    FileInputStream fileInputStream = new FileInputStream(excelFilePath);
    Workbook workbook = WorkbookFactory.create(fileInputStream);
    Sheet sheet = workbook.getSheet(sheetName);

    int rowCount = sheet.getLastRowNum();
    int colCount = sheet.getRow(0).getLastCellNum();

    Object[][] data = new Object[rowCount][1];
    for (int i = 1; i <= rowCount; i++) {
        Row row = sheet.getRow(i);
        Cell cell = row.getCell(columnToRead, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        data[i - 1][0] = cell.getStringCellValue();
    }


    return data;
}

@BeforeMethod
public void beforeMethod() throws MalformedURLException {
    openBrowser();

}

    @AfterMethod
    public void afterMethod() {
        driver.quit();
       
      
    }
}
