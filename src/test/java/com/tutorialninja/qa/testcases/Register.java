package com.tutorialninja.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.base.Base;
import com.tutorial.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Register extends Base {
	
	public Register() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod()
	public void setup() {
		driver= browserAndurl(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
	
	}
	
	@AfterMethod()
	public void teardown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
		public void VerifyRegisterMandatory() {
		
		
		//driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Testpwd");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimestamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123654789");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).sendKeys("Test1");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String acttext=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(acttext, "Your Account Has Been Created!","Account not created");
			
	}

	
	@Test(priority=2)
	public void VerifynonMandatoryfield() {
		
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Testpwd");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimestamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123654789");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Test1");
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String acttext=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(acttext, "Your Account Has Been Created!","Account not created");
	}
	
	
	@Test(priority=3)
	public void Verifysameemail() {
		
		
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Testpwd");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("manjula.nj415@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("123654789");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Test1");
		//driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).sendKeys("Test1");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String acttext=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(acttext, "Warning: E-Mail Address is already registered!","Account not created");
		
	}
	
	@Test(priority=4)
	public void Verifywarning() {

		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
				
		String acttext=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(acttext, "Warning: You must agree to the Privacy Policy!","warning msg");
		
		String actfirstname= driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actfirstname, "First Name must be between 1 and 32 characters!","First name msg");
		
		
	}
	
	
}
