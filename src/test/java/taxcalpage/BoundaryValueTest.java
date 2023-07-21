package taxcalpage;

import io.github.bonigarcia.wdm.WebDriverManager;
import taxcalpage.controller.WebDriverController;
import taxcalpage.pages.TaxCalculatorPage;
import taxcalpage.pages.TaxCalculatorPageImp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BoundaryValueTest {

    WebDriver driver;
   TaxCalculatorPage taxCalPage;

    @BeforeMethod
    public void setUp() {


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

    //$1
    @Test
    public void taxBracketA_MinIncome() {


        this.taxCalPage.calculate(1.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$0.11");

//        Assert.assertEquals(expectedTaxBracket, actualTaxBracket);

    }

    //$14000
    @Test
    public void taxBracketA_MaxIncome() {


        this.taxCalPage.calculate(14000.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$1470");
    }

    //$14001
    @Test
    public void taxBracketB_MinIncome() {


        this.taxCalPage.calculate(14001.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$2450.17");
    }

    //$48000
    @Test
    public void taxBracketB_MaxIncome() {


        this.taxCalPage.calculate(48000.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$8400");
    }

    //$48001
    @Test
    public void taxBracketC_MinIncome() {


        this.taxCalPage.calculate(48001.0);

        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$14400.3");
    }

    //$70000
    @Test
    public void taxBracketC_MaxIncome() {

        this.taxCalPage.calculate(70000.0);

        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$21000");
    }

    //$70001
    @Test
    public void taxBracketD_MinIncome() {

        this.taxCalPage.calculate(70001.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$23100.33");
    }

    //$180000
    @Test
    public void taxBracketD_MaxIncome() {


        this.taxCalPage.calculate(180000.0);

        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$59400");
    }


    //$180001
    @Test
    public void taxBracketE_MinIncome() {


        this.taxCalPage.calculate(180001.0);
        Assert.assertEquals(this.taxCalPage.readSummaryReportTaxToBePaid(), "$70200.39");
    }

}









