package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import config.ConfigReader;

import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {

	@Test(priority = 1)
	public void loginCredentials() throws Exception {
		Testcase = extentReport.createTest("Login Test");

		// Read login credentials from config file
		ConfigReader config = new ConfigReader();
		String user = config.getUsername();
		String givenpassword = config.getPassword();

		// Enter Email
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys(user);
		Testcase.log(Status.INFO, "Entered Email: " + user);

		// Enter Password
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(givenpassword);
		Testcase.log(Status.INFO, "Entered Password: ********"); // Masked password for security

		// Click Login Button
		Thread.sleep(1000);
		WebElement login = driver.findElement(By.xpath("(//*[.='Login'])[2]"));
		login.click();
		Testcase.log(Status.INFO, "Clicked Login Button");

		// Verify Login by Checking Page Title
		Thread.sleep(2000);
		String expectedTitle = "McPheeFreight";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

		if (actualTitle.contains(expectedTitle)) {
			Testcase.log(Status.PASS, "Login successful, navigated to Dashboard.");
		} else {
			Testcase.log(Status.FAIL, "Login failed, incorrect page title: " + actualTitle);
		}

		Assert.assertTrue(actualTitle.contains(expectedTitle), "Login Test Failed");
	}


	@Test(priority = 2)
	public void settingsSelect() throws Exception {
		Testcase = extentReport.createTest("Settings Selection");
		Thread.sleep(2000);

		Testcase.log(Status.INFO, "Navigating to Settings Page");

		// Click on Settings Tab
		WebElement settingsTab = driver.findElement(By.xpath("//a[@class='nav-link menu-title']//span[text()='Settings']"));
		settingsTab.click();
		Testcase.log(Status.PASS, "Settings tab clicked successfully");

		Thread.sleep(1000);
		System.out.println("*--------------------------Selecting Shipping Line -----------------------------------*");

		Thread.sleep(1000);
		WebElement shippinglineSelect = driver.findElement(By.xpath("//ul[@class='nav-submenu menu-content active menu-open']//span[text()='Shipping line']"));
		shippinglineSelect.click(); 

		Testcase.log(Status.PASS, "Shipping Line tab clicked successfully");

	}

	@Test(priority = 3)
	public void addShippingLine() {
		Testcase = extentReport.createTest("Add Shipping Line");

		WebElement addShippingLine = driver.findElement(By.xpath("//span[text()='Add new shipping line']"));
		addShippingLine.click(); 
		Testcase.log(Status.PASS, "Clicked Add new shipping line button");
	}

	@Test(priority = 4)
	public void createShippingLine() throws Exception {
		Testcase = extentReport.createTest("Create Shipping Line");

		String shippinglineData = "AUS - AME";
		WebElement shippingline = driver.findElement(By.xpath("//input[@formcontrolname='shippingLine']"));
		shippingline.sendKeys(shippinglineData);
		Testcase.log(Status.INFO, "Entered Shipping Line: " + shippinglineData);

		// Save click
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		Testcase.log(Status.PASS, "Shipping Line added successfully");

		System.out.println("SHIPPING LINE ADDED SUCCESSFULLY");
	}

	@Test(priority = 5)
	public void searchShippingLineId() throws Exception {
		Testcase = extentReport.createTest("Search Shipping Line");

		String searchShippingLineData = "AUS - AME";
		Thread.sleep(2000);
		WebElement searchline = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
		searchline.sendKeys(searchShippingLineData);
		Testcase.log(Status.INFO, "Searched for Shipping Line: " + searchShippingLineData);
	}

	@Test(priority = 6)
	public void editShippingLineId() throws InterruptedException {
		Testcase = extentReport.createTest("Edit Shipping Line");

		String editShippingLineData = "AUS - USA";

		WebElement editButton = driver.findElement(By.xpath("//i[@class='fa fa-edit']"));
		editButton.click();
		Testcase.log(Status.INFO, "Clicked Edit Button");

		Thread.sleep(2000);
		WebElement shippingline = driver.findElement(By.xpath("//input[@formcontrolname='shippingLine']"));
		shippingline.clear();
		Thread.sleep(2000);
		shippingline.sendKeys(editShippingLineData);
		Testcase.log(Status.INFO, "Updated Shipping Line to: " + editShippingLineData);

		// Save click
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		Testcase.log(Status.PASS, "Shipping Line updated successfully");

		System.out.println("SHIPPING LINE UPDATED SUCCESSFULLY");
	}

	@Test(priority = 7)
	public void deleteShippingLineId() throws Exception {
		Testcase = extentReport.createTest("Delete Shipping Line");

		String deleteShippingLineData = "AUS - USA";
		WebElement searchline = driver.findElement(By.xpath("//input[@placeholder='Search...']"));

		Thread.sleep(1000);
		searchline.clear();
		Thread.sleep(2000);
		searchline.sendKeys(deleteShippingLineData);
		Testcase.log(Status.INFO, "Searched for Shipping Line to delete: " + deleteShippingLineData);

		WebElement deleteButton = driver.findElement(By.xpath("//i[@class='fa fa-trash']"));
		deleteButton.click();
		Testcase.log(Status.PASS, "Clicked Delete Button");

		Thread.sleep(2000);
	}

	@Test(priority = 8)
	public void deleteShippingLineYES() throws Exception {
		Testcase = extentReport.createTest("Confirm Deletion of Shipping Line");

		WebElement yes = driver.findElement(By.xpath("//button[@class='swal2-confirm btn btn-success']"));
		yes.click();
		Testcase.log(Status.PASS, "Confirmed deletion");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Testcase.log(Status.INFO, "Deletion successful");

		System.out.println("SHIPPING LINE IS DELETED SUCCESSFULLY");
	}

	@Test(priority = 9)
	public void deleteShippingLineNO() {
		Testcase = extentReport.createTest("Cancel Deletion of Shipping Line");

		WebElement no = driver.findElement(By.xpath("//button[@class='swal2-cancel btn btn-danger']"));
		no.click();
		Testcase.log(Status.INFO, "Cancelled deletion");

		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Testcase.log(Status.PASS, "Deletion canceled successfully");

		System.out.println("CANCELLED NOT PERFORMED");
	}
}
