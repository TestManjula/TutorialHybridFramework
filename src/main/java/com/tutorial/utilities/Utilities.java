package com.tutorial.utilities;

import java.util.Date;

public class Utilities {
	
	public static final int IMPICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=10;
	
public static String generateTimestamp() {
		
		Date date=new Date();
		String timestamp=date.toString().replace(" ","_").replace(":","_");
		return "manju"+timestamp+"@gmail.com";	
	}

}
