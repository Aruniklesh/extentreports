package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public ExtentReports extentReport;
    protected ExtentTest Testcase;
    ExtentSparkReporter spark;

    @BeforeClass
    public void preConditions() {
        // Initialize ExtentReports
        extentReport = new ExtentReports();
        spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extentReport.attachReporter(spark);

        Testcase = extentReport.createTest("Verify MCPHEE");
        Testcase.log(Status.INFO, "Initializing Browser and Navigating to MCPHEE");

        // Launch Browser
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        Testcase.log(Status.INFO, "Chrome Browser Launched and Maximized");

        // Navigate to Login Page
        String url = "https://mcphee.ishtech.com.au/#/auth/login";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Testcase.log(Status.INFO, "Navigated to: " + url);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extentReport.flush();
        Testcase.log(Status.INFO, "Browser Closed");
    }
}
