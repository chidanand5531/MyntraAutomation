package com.myntra.testscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.myntra.driverscript.DriverScript;
import com.myntra.utility.ObjectMap;

public class CartPage extends DriverScript {

	public static WebDriverWait wait;
	public static Actions action;

	public static String addToBag(WebDriver obrowser) {

		try {

			log.info("execution of addToBag method started here... ");
			action = new Actions(obrowser);
			wait = new WebDriverWait(obrowser, 30);
			WebElement searchBar = ObjectMap.getWebElement("SearchBar");
			wait.until(ExpectedConditions.visibilityOf(searchBar));
			searchBar.sendKeys("Nike shoes");
			ObjectMap.getWebElement("SearchButton").click();
			WebElement product = ObjectMap.getWebElement("Firstproduct");
			action.moveToElement(product).perform();
			ObjectMap.getWebElement("AddToBag").click();
			WebElement sizeButton = ObjectMap.getWebElement("SizeButton");
			action.moveToElement(sizeButton).perform();
			sizeButton.click();
			Thread.sleep(2000);
			WebElement textMessage = ObjectMap.getWebElement("Notification");
			action.moveToElement(textMessage).perform();
			String Message = textMessage.getText();
			Assert.assertEquals(Message, "Added to bag", "Notification Message not matched");
			log.info("execution of addToBag method successfully ended ");

		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}



	public static String removeFromBag(WebDriver obrowser) {

		try {
			log.info(" execution of removeFromBag method started..");
			ObjectMap.getWebElement("BagIcon").click();
			log.info("clicked on bag ");
			ObjectMap.getWebElement("RemoveButton").click();
			ObjectMap.getWebElement("RemoveItem").click();
			log.info(" execution of removeFromBag method successfully ended");
			Thread.sleep(5000);
			WebElement MyntraLogo = ObjectMap.getWebElement("MyntraLogo");
			wait.until(ExpectedConditions.visibilityOf(MyntraLogo)).click();
			log.info(" returned to HomePage successfully");

		} catch (Exception e) {
			return "Fail";
		}
		return "Pass";
	}

}
