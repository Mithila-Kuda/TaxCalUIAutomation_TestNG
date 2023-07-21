package taxcalpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import taxcalpage.pages.TaxCalculatorPage;
import taxcalpage.pages.TaxCalculatorPageImp;

import java.util.concurrent.TimeUnit;

public class SummaryReportBehaviourTest {
    WebDriver driver;
    TaxCalculatorPage taxCalPage;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://24.199.68.169:3000/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //taxCalculatorPage is an object, and it helps to call 'TaxCalculatorPageImp' constructor method
        //This allows the test methods to interact with the web page using the implemented methods in the TaxCalculatorPage interface.

        taxCalPage = new TaxCalculatorPageImp(driver);

    }

    @AfterMethod
    public void wrapUp() {

        driver.quit();
    }

    @Test
    public void verifyTaxCal_SummaryReport_Behavior() {

        this.taxCalPage.calculate(12345);

        boolean reportStatusVisible = this.taxCalPage.CheckSummaryReportStatus();

        //Report should disappear,but its visible still
        Assert.assertFalse(reportStatusVisible, "Previous 'Tax result' is displaying still in the Summary report ");

    }


}
