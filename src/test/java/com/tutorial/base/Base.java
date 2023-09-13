package com.tutorial.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorial.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base() {
		prop=new Properties();
		
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\config\\Config.properties");
		
		try {
		FileInputStream fis= new FileInputStream(propFile);
		prop.load(fis);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		dataProp=new Properties();
		File datapropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\testdata\\testdata.properties");
		try {
			FileInputStream fis2= new FileInputStream(datapropFile);
			dataProp.load(fis2);
			} catch(Throwable e) {
				e.printStackTrace();
			}
	}
	
	/*public void loadPropertiesFile() {
		prop=new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\config\\Config.properties");
		
		try {
		FileInputStream fis= new FileInputStream(propFile);
		prop.load(fis);
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}*/
	
	public WebDriver browserAndurl(String browsername) {
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}else if(browsername.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			//driver=new EdgeDriver();
		}
				
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Utilities.IMPICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Utilities.PAGE_WAIT_TIME, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		return driver;
		
	}

}
