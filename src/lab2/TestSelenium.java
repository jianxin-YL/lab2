package lab2;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestSelenium {
	  private WebDriver driver;
	  private String baseUrl;
	  
	  private String studentId = null;
	  private String passWord = null;
	  private String studentName = null;
	  private String gitAdd = null;

	  
	  @Before
	  public void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/src/lab2/geckodriver.exe";
		  System.setProperty("webdriver.gecko.driver", driverPath);
		 // baseUrl = "https://www.baidu.com/";
		    baseUrl = "http://121.193.130.195:8800";
		 // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    
	  }

	  @Test
	  public void testSelenium() throws Exception {
		  
		    String file_dir = "C:\\Users\\HP\\Desktop\\软件测试技术\\软件测试名单.xlsx";
	        Workbook book = null;
	        FileInputStream fis = null;   
	        fis = new FileInputStream(file_dir);  
	        book = WorkbookFactory.create(fis); 
	        
		    Sheet sheet = book.getSheetAt(0);  
		    int lastRowNum = sheet.getLastRowNum();
		    
		  for(int i = 2 ; i <= 10 ; i++){
	            Row row = null;
	            row = sheet.getRow(i);
	            if( row != null ){
	                //System.out.println("reading line is " + i);
	                int lastCellNum = row.getLastCellNum();	                
	               // System.out.println("lastCellNum is " + lastCellNum );
	                Cell cell = null;
	                String cellValue = null;
	               
	                for( int j =1  ; j <= lastCellNum ; j++ ){
	                    cell = row.getCell(j);
	                    if( cell != null ){
	                    	cell.setCellType(CellType.STRING);
	                        cellValue = cell.getStringCellValue();
	                        
	                        System.out.print("cell value is  " + cellValue);
	                    }
	                    else {
	                    	cellValue = null;
	                    }
	                    
	                    if(j==1) {
	                    	studentId = cellValue;
	                    	passWord= studentId.substring(studentId.length() - 6);
	                    }
	                    else if(j==2) {
	                    	studentName = cellValue;
	                    }
	                    else if(j==3) {
	                    	gitAdd = cellValue;
	                    }
	                    else {
	                    	System.out.println("Something wrong");
	                    }	
	                } 
	      driver = new FirefoxDriver();
	      driver.get(baseUrl);
	      WebElement element = driver.findElement(By.id("login_form"));
	      driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.SECONDS);
	                
		  element = driver.findElement(By.name("id"));
		  element.sendKeys(studentId);
		  element = driver.findElement(By.name("password"));
		  element.sendKeys(passWord);
		  element = driver.findElement(By.id("btn_login"));
		  element.click(); 
		  driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.SECONDS);
		  
		  element = driver.findElement(By.id("student-id"));
		  assertEquals(studentId,element.getText());  
		  element = driver.findElement(By.id("student-name"));
		  assertEquals(studentName,element.getText());  
		  element = driver.findElement(By.id("student-git"));
		  assertEquals(gitAdd,element.getText());
		  
		  driver.close();
	  }
	}
 }

}
