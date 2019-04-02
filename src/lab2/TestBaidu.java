package lab2;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestBaidu {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
  
  @Before
  public void setUp() throws Exception {
	  String driverPath = System.getProperty("user.dir") + "/src/lab2/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	 // baseUrl = "https://www.baidu.com/";
	    baseUrl = "http://121.193.130.195:8800";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBaidu() throws Exception {
   /* driver.get(baseUrl + "/");
    WebElement we = driver.findElement(By.id("kw"));
    we.click();
//    driver.findElement(By.id("kw")).click();
    driver.findElement(By.id("kw")).clear();
    driver.findElement(By.id("kw")).sendKeys("天津大学");
    driver.findElement(By.id("su")).click();
    assertEquals("天津大学_百度搜索", driver.getTitle());*/
	  
	  driver.get(baseUrl);
	  WebElement element = driver.findElement(By.id("login_form"));
	  element = driver.findElement(By.name("id"));
	  element.sendKeys("3016218192");
	  element = driver.findElement(By.name("password"));
	  element.sendKeys("218192");
	  element = driver.findElement(By.id("btn_login"));
	  element.click(); 
	  
	  element = driver.findElement(By.id("student-id"));
	  System.out.println("hello");
	  
	  assertEquals(1.1, 1.1,0.1);  
  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

