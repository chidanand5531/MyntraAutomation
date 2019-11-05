package com.myntra.utility;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.myntra.driverscript.DriverScript;

public class ObjectMap extends DriverScript {

	public static WebElement getWebElement(String logicalname) {

		String locators = objectMap.getProperty(logicalname);
		String arrlocator[] = locators.split(">");
		String locatorName = arrlocator[0];
		String locatorValue = arrlocator[1];
		WebElement webElement = null;

		try {
			if (locatorName.equalsIgnoreCase("xpath")) {
				webElement = obrowser.findElement(By.xpath(locatorValue));

			} else if (locatorName.equalsIgnoreCase("id")) {
				webElement = obrowser.findElement(By.id(locatorValue));

			} else if (locatorName.equalsIgnoreCase("name")) {
				webElement = obrowser.findElement(By.name(locatorValue));

			} else if (locatorName.equalsIgnoreCase("cssSelector")) {
				webElement = obrowser.findElement(By.cssSelector(locatorValue));

			} else if (locatorName.equalsIgnoreCase("linkText")) {
				webElement = obrowser.findElement(By.linkText(locatorValue));

			} else if (locatorName.equalsIgnoreCase("tagName")) {
				webElement = obrowser.findElement(By.tagName(locatorValue));

			} else if (locatorName.equalsIgnoreCase("partialLinkText")) {
				webElement = obrowser.findElement(By.partialLinkText(locatorValue));

			} else if (locatorName.equalsIgnoreCase("className")) {
				webElement = obrowser.findElement(By.className(locatorValue));
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.error(locatorName + " Element not found ");
			Assert.fail(locatorName + " Element not found ");
		}
		return webElement;
	}
	
	
	public static void buttonClick(String locator, String name){

		try{

		if(getWebElement(locator).isEnabled()){

		getWebElement(locator).click();
		log.info("Clicked on "+"'"+name+"'"+ " button");

		}else
		{
		log.error("'"+name+"'" + " : Button is not enabled");
		Assert.fail("'"+name+"'" + " : Button is not enabled");
		}

		}catch(NoSuchElementException e){
		e.printStackTrace();
		log.error(locator + " : Button not found");
		Assert.fail(locator + " : Button not found");

		}catch(ElementNotVisibleException e){

		e.printStackTrace();
		log.error(locator + " : Button not visible");
		Assert.fail(locator + " : Button not visible");

		}
		}

		public static boolean isDisplayed(String locator, String name){

		boolean flag=false;
		try{

		if(getWebElement(locator).isDisplayed()){

		log.info("Element "+"'"+name+"'"+ " is present");
		flag=true;

		}else{
		log.error("'"+name+"'" + " : Element is not present");
		Assert.fail("'"+name+"'" + " : Element is not present");
		flag=false;
		}

		}catch(NoSuchElementException e){

		e.printStackTrace();
		log.error(locator + " : Element not found");
		Assert.fail(locator + " : Element not found");
		flag=false;

		}catch(ElementNotVisibleException e){

		e.printStackTrace();
		log.error(locator + " : Element not visible");
		Assert.fail(locator + " : Element not visible");
		flag=false;

		}
		return flag;
		}
		
		public static void SetValueToWebElement(String locator, String name, String testdata) {
			
			try {
				
				if(getWebElement(locator).isEnabled()) {
					getWebElement(locator).sendKeys(testdata);
					log.info("Entered " + testdata + " in " + "," + name + "," + "field ");	
				}else {
					log.error(","+ name + "," + " : field not present");
					Assert.fail(","+ name + "," + " : field not present");
				}
				
			}catch(NoSuchElementException e) {
				e.printStackTrace();
				log.error(locator+" : field not found");
				Assert.fail(locator+" : field not found");
			}catch(ElementNotVisibleException e) {
				e.printStackTrace();
				
				log.error(locator+" : field not visible");
				Assert.fail(locator+" : field not visible");
			}
			
			
		}

}
