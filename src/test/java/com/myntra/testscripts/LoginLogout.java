package com.myntra.testscripts;

import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.myntra.driverscript.DriverScript;
import com.myntra.utility.ObjectMap;

public class LoginLogout extends DriverScript {

	public static Actions action;
	public static WebDriverWait wait;

	public static String validateTitle(WebDriver obrowser) {
		try {
			String Title = obrowser.getTitle();
			Assert.assertEquals(Title, "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra",
					"Title not matched");
			log.info("validateTitle method successfully ended");
		} catch (Exception e) {
			return "Fail";
		}
		return "Pass";
	}
	
	
	public static String validateMyntraLogo(WebDriver obrowser) {

		try {
			boolean flag=ObjectMap.getWebElement("myntralogo").isDisplayed();
			Assert.assertTrue(flag, "MyntraLogo not displayed");
			log.info("validateMyntraLogo method successfully ended");
		} catch (Exception e) {
			return "Fail";
		}
		return "Pass";
	}

	public static String login(WebDriver obrowser) {

		try {
			log.info("loginMethod started here.. ");
			
			Pattern testdataPattern=Pattern.compile(",");
			String arrTestData[]=testdataPattern.split(testDataColumn);
			String email=datatable.getCellData(testScriptExcelFle, "testdata", arrTestData[0], 2).trim();
			String password=datatable.getCellData(testScriptExcelFle, "testdata", arrTestData[1], 2).trim();
	
			if(ObjectMap.isDisplayed("profileImage", "profile")) {
				action = new Actions(obrowser);
				action.moveToElement( ObjectMap.getWebElement("profileImage")).build().perform();
				ObjectMap.buttonClick("Loginbutton", "Login");
			}	
			if(ObjectMap.isDisplayed("textLoginUserName", "Email")) {
				ObjectMap.SetValueToWebElement("textLoginUserName", "Email", email);				
			}
           if(ObjectMap.isDisplayed("textLoginPassword", "Password")) {
				ObjectMap.SetValueToWebElement("textLoginPassword", "Password", password);				
           }
           if(ObjectMap.isDisplayed("UserLoginBtn", "Login")) {
				ObjectMap.buttonClick("UserLoginBtn", "Login");				
			}
			log.info("loginMethod successfully ended ");
		} catch (Exception e) {
			log.error("there is exception araised during the execution of Method Login: "+e);
			return "Fail";
		}
		return "Pass";
	}

	public static String verifyUser(WebDriver obrowser) {

		try {
			log.info("VerifyUser method started here.. ");
			Thread.sleep(1000);
			if(ObjectMap.isDisplayed("profileImage", "profile")) {
				action = new Actions(obrowser);
				action.moveToElement( ObjectMap.getWebElement("profileImage")).build().perform();
			}
			String UserID = ObjectMap.getWebElement("UserLabel").getText();
			Assert.assertEquals(UserID, config.getProperty("email"), "UserID not matched");
			log.info("VerifyUser method successfully ended");
		} catch (Exception e) {
			return "Fail";
		}
		return "Pass";
	}

	public static String logout(WebDriver obrowser) {

		try {
			log.info("Execution Of the Logout method  started here..");
			obrowser.navigate().refresh();
			
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			 public Boolean apply(WebDriver driver) {
			 return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			 }
			 };
			
			 try {
				 
			Thread.sleep(5000);
			 WebDriverWait wait = new WebDriverWait(obrowser, 30);
			 wait.until(expectation);
			 } catch (Throwable error) {
			 Assert.fail("Timeout waiting for pageload Request to complete");
			 }
			
			WebElement profile1 = ObjectMap.getWebElement("profileImage");
			WebElement LogoutButton=ObjectMap.getWebElement("LogoutButton");
			action.moveToElement(profile1).moveToElement(LogoutButton).click(LogoutButton).build().perform();
			
			
			log.info("Execution Of the Logout method successfully ended");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("there is an exception arised during execution of Logout method :" + e);
			return "Fail";
		}
		return "Pass";

	}

}
