package com.myntra.testscripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.myntra.driverscript.DriverScript;
import com.myntra.utility.ObjectMap;

public class WishList extends DriverScript {

	public static WebDriverWait wait;
	public static String ProductName;
	public static Actions action;

	public static String addToWishList(WebDriver obrowser) {

		try {

			log.info("execution of addToWishList method started.. ");

			action = new Actions(obrowser);
			wait = new WebDriverWait(obrowser, 30);
			WebElement searchBar = ObjectMap.getWebElement("SearchBar");
			wait.until(ExpectedConditions.visibilityOf(searchBar));
			searchBar.sendKeys("Nike shoes");
			ObjectMap.getWebElement("SearchButton").click();
			WebElement product = ObjectMap.getWebElement("Firstproduct");
			action.moveToElement(product).perform();
			ObjectMap.getWebElement("WishList").click();
			Thread.sleep(3000);
			WebElement textMessage = ObjectMap.getWebElement("Notification");
			wait.until(ExpectedConditions.visibilityOf(textMessage));
			String Message = textMessage.getText();
			Assert.assertEquals(Message, "Added to wishlist", "Notification Message not matched");
			log.info("execution of addToWishList method successfully ended.. ");

		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

	public static String moveWishListToBag(WebDriver obrowser) {

		try {

			log.info("execution of moveWishListToBag method started.. ");
			ObjectMap.getWebElement("WishListImage").click();
			ProductName = ObjectMap.getWebElement("FirstProoductName").getText();
			System.out.println(ProductName);
			ObjectMap.getWebElement("MoveToBag").click();
			ObjectMap.getWebElement("wishListSizeButton").click();
			ObjectMap.getWebElement("DoneButton").click();
			ObjectMap.getWebElement("BagIcon").click();
			log.info("execution of moveWishListToBag method successfully ended.. ");

		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

	public static String placeOrder(WebDriver obrowser) {

		try {

			log.info(" execution of placeOrder method started..");
			WebElement placeorder = ObjectMap.getWebElement("PlaceOrder");
			wait.until(ExpectedConditions.visibilityOf(placeorder)).click();

			String Name = config.getProperty("Name");
			String MobileNo = config.getProperty("MobileNo");
			String Pincode = config.getProperty("Pincode");
			String Address_Area = config.getProperty("Address_Area");
			Thread.sleep(5000);

			ObjectMap.getWebElement("Name").sendKeys(Name);
			ObjectMap.getWebElement("MobileNo").sendKeys(MobileNo);
			ObjectMap.getWebElement("Pincode").sendKeys(Pincode);
			ObjectMap.getWebElement("Address_Area").sendKeys(Address_Area);
			ObjectMap.getWebElement("Locality").click();
			WebElement villagename = ObjectMap.getWebElement("VillageName");
			JavascriptExecutor js = (JavascriptExecutor) obrowser;
			js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);",villagename );
			wait.until(ExpectedConditions.visibilityOf(villagename));
			villagename.click();

			WebElement MyntraLogo = ObjectMap.getWebElement("MyntraLogo");
			wait.until(ExpectedConditions.visibilityOf(MyntraLogo)).click();

			log.info("execution of placeOrder method ended.. ");
		} catch (Exception e) {
			e.printStackTrace();

			return "Fail";
		}
		return "Pass";
	}

}
