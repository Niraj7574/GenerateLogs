package com.tests;

import java.lang.System.Logger;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	
	//What is log?: capturing info/activities at the time of the program execution
	// types of logs:
		//1. info
		//2. warn
		//3. error
		//4. fatal
	
	//How to generate the logs?: Use Apache log4j API (Log4j API)
	//How it works?: it reads 4j configuration from log4j.properties file
	//Where to create log4j.properties?: create inside the resources folder

	WebDriver driver;
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginTest.class);
	

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:\\QA\\SeleniumJars\\geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("launching Chrome Browser");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://cogmento.com/");
		log.info("Entering application URL");
	}

	@Test(priority = 1)
	public void validateTitle() {
		String title = driver.getTitle();
		log.info("checking loggin page title");
		Assert.assertEquals(title, "Cogmento CRM and Business Cloud Solutions");
	}

	@Test(priority = 2)
	public void validateLogo() {
		boolean flag = driver.findElement(By.linkText("your business cloud partner")).isDisplayed();
		Assert.assertTrue(flag);
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
