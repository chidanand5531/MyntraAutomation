package com.myntra.testscripts;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.myntra.driverscript.DriverScript;


public class Initialize extends DriverScript {

	public static WebDriver obrowser;

	public static WebDriver launch() {
		String browsertype;

		try {
			
			log.info(" ChromeBrowser launched successfully");
			browsertype = config.getProperty("browsertype");
			//browsertype=System.getenv("BrowserType");
			switch (browsertype.toLowerCase()) {

			case "firefox":
				log.info(" FirefoxBrowser launched successfully");
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Library/drivers/geckodriver.exe");
				obrowser = new FirefoxDriver();
				break;

			case "chrome":
				ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Library/drivers/chromedriver.exe");
				options.addArguments("--desable-notifications");
				obrowser = new ChromeDriver(options);
				break;

			case "ie":
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/Library/drivers/IEDriverServer.exe");
				obrowser = new InternetExplorerDriver();
				break;

			default:
				System.out.println(" Invalid BrowserName !!!!!!!!!!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obrowser;
	}

	public static String navigate(WebDriver obrowser) {

		String url;

		try {
			log.info("The excution of the navigate  method started here... ");
			obrowser.manage().window().maximize();
			//url = config.getProperty("url");
			Pattern testdataPattern=Pattern.compile(",");
			String arrTestData[]=testdataPattern.split(testDataColumn);
			url=datatable.getCellData(testScriptExcelFle, "testdata", arrTestData[0], 2);
			
			obrowser.get(url);
			obrowser.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			log.info("The excution of the navigate  method ended... ");
		} catch (Exception e) {
			return "fail";
		}
		return "pass";
	}

	public static String closeApplication(WebDriver obrowser) {

		try {
			obrowser.close();
		} catch (Exception e) {
			return "fail";
		}
		return "pass";
	}
}
