package taxcalpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import taxcalpage.pages.TaxCalculatorPage;
import taxcalpage.pages.TaxCalculatorPageImp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ErrorHandlingTest {


    WebDriver driver;
    TaxCalculatorPage taxCalPage;

    @BeforeClass
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://24.199.68.169:3000/");
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

        taxCalPage= new TaxCalculatorPageImp(driver);


    }

//    @AfterMethod
//    public void wrapUp() {
//
//        driver.quit();
//    }


    @Test
    public void verifyErrorMessageDelivery() {

        this.taxCalPage.calculate(0.00);
        Assert.assertEquals(this.taxCalPage.readErrorMessage(), "An error occurred while calculating tax.");


    }


}
