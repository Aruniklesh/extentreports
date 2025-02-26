package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.BaseTest;
import com.aventstack.extentreports.Status;

public class Settings extends BaseTest {

	@Test(priority = 2)
    public void settingsSelect() throws Exception {
        Thread.sleep(2000);
        System.out.println("*--------------------------Setting-----------------------------------*");
        Testcase.log(Status.INFO, "Navigating to Settings Page");

        // Click on Settings Tab
        WebElement settingsTab = driver.findElement(By.xpath("//a[@class='nav-link menu-title']//span[text()='Settings']"));
        settingsTab.click();
        Testcase.log(Status.PASS, "Settings tab clicked successfully");

        Thread.sleep(1000);
    }
}
