package com.tutorialninja.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class test1 {

	public static void main(String[] args) {
		Properties prop=new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\config\\Config.properties");
		
		try {
			FileInputStream fis= new FileInputStream(propFile);
			prop.load(fis);
			} catch(Throwable e) {
				e.printStackTrace();
			}
		
//System.out.println(propFile);
//System.out.println(prop.getProperty("browserName"));




	Properties dataProp=new Properties();
	File datapropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorial\\testdata\\testdata.properties");
try {
	FileInputStream fis2= new FileInputStream(datapropFile);
	dataProp.load(fis2);
	} catch(Throwable e) {
		e.printStackTrace();
	}
System.out.println(datapropFile);

System.out.println(dataProp.getProperty("invalidpassword"));
System.out.println(dataProp.getProperty("invalidpassword"));



	}
	
}
