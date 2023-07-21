package taxcalpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import taxcalpage.pages.TaxCalculatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import taxcalpage.pages.TaxCalculatorPageImp;

import java.util.concurrent.TimeUnit;

public class PerformanceTest {
    WebDriver driver;
    TaxCalculatorPage taxCalPage;

    @BeforeClass
    public void setUp_PerformanceTest() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://24.199.68.169:3000/");
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

        taxCalPage= new TaxCalculatorPageImp(driver);
    }

    @AfterMethod
    public void wrapUp() {

        driver.quit();
    }


    @Test
    public void verifyTaxCal_Performance() {
        int timeTaken = this.taxCalPage.getTimeToOpenTheCalculator();
        Assert.assertEquals(timeTaken <= 30, timeTaken <= 30);

        //below line won't execute if above line fail.
        System.out.println("Tax Calculator appeared with considerable " + timeTaken + " milliseconds time");
    }


}
