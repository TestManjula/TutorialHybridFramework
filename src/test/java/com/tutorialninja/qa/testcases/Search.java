package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.base.Base;


//comments added
public class Search extends Base{
	
	public Search() {
		super();
	}
	
	
	WebDriver driver;
	
	@BeforeMethod()
	public void setup() {
		driver=browserAndurl(prop.getProperty("browserName"));
	}
	@AfterMethod()
	public void teardown() {
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifysearchValidProduct() {
	
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product is not displayed");
	}
	
	@Test(priority=2)
	public void VerifyInvalidProduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		String actsearchmsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actsearchmsg, "There is no product that matches the search criteria.","No message dispalyed");
		
	}
	@Test(priority=3)
	public void VeriysearchNpProduct() {
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		String actsearchmsg=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actsearchmsg, "There is no product that matches the search criteria.","No message dispalyed");
		
	}

}
