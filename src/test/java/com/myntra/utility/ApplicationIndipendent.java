package com.myntra.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ApplicationIndipendent {

	public static Properties property(String filename) {
		FileInputStream fin = null;
		Properties prop = null;

		try {
			fin = new FileInputStream(filename);
			prop = new Properties();
			prop.load(fin);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	

	public static String getDateTime(String sDateFormat) {
		String sDateTime = null; 
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(sDateFormat);
			sDateTime = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDateTime;
	}
	

	public static void captureScreenshot(WebDriver oBrowser, String FileName) {
		File sourceFile;
		try {
			sourceFile = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(FileName));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
