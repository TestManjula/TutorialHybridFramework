package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.base.Base;
import com.tutorial.utilities.Utilities;

public class Login extends Base {
	
	public Login() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod()
	public void setup() {
		//loadPropertiesFile();
		driver= browserAndurl(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();
		
	}
	
	@AfterMethod()
	public void teardown() {
		
		driver.quit();
	}
	
	@Test(priority=3)
	public void verifylogincredencial() {
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Edit your account information']")).isDisplayed());
		
		}
	
	@Test(priority=2)
	public void verifyloginwithinvalidcredential() {
		
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimestamp());
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(dataProp.getProperty("invalidpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actmessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String Expmessage=dataProp.getProperty("emailPasswordNomatch");  
		System.out.println(actmessage);
		Assert.assertTrue(actmessage.contains(Expmessage), "Expected warning msg did not display");

		
		}
	
	@Test(priority=1)
	
	public void verifyLoginwithValidEmailandInvalidPassword() {
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("manjula.nj415@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12b334k345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actmessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String Expmessage="No match for E-Mail Address and/or Password.";
		System.out.println(actmessage);
		Assert.assertTrue(actmessage.contains(Expmessage), "Expected warning msg did not display");

	
		
	}
	
	
	

}
