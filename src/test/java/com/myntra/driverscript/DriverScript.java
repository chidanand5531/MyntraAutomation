package com.myntra.driverscript;

import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.myntra.datatable.Datatable;
import com.myntra.testscripts.CartPage;
import com.myntra.testscripts.Initialize;
import com.myntra.testscripts.LoginLogout;
import com.myntra.testscripts.WishList;
import com.myntra.utility.ApplicationIndipendent;
import com.myntra.utility.ReportUtil;

public class DriverScript {

	public static WebDriver obrowser;
	public static Properties config;
	public static Properties objectMap;
	public static Datatable datatable;
	public static String controllerFile;
	public static String testDataColumn;
	public static String testScriptExcelFle;
	public static String testScriptStatus = "";
	public static String testDataTable;

	public static Logger log = LogManager.getLogger(DriverScript.class);

	@BeforeSuite
	public void startScript() {
		String sDateTime;
		try {

			String ResultReportFileName = System.getProperty("user.dir")
					+ "/Results/ResultsReports/SummaryResultReports.html";
			sDateTime = ApplicationIndipendent.getDateTime("dd-MM-yyyy h:m:s z");
			ReportUtil.createReport(ResultReportFileName, sDateTime, "QA Testing");
		} catch (Exception e) {
			log.error("there is an exception araised during execution of the methos startScript, the exception  " + e);
		}
	}

	@BeforeClass
	public void loadFiles() {

		try {
			log.info("execution of loadingfiles method started here..");
			String configFile = System.getProperty("user.dir") + "/Configuration/Configuration.Properties";
			config = ApplicationIndipendent.property(configFile);

			String ObjectMapFile = System.getProperty("user.dir") + "/ObjectRepository/ObjectRepo.Properties";
			objectMap = ApplicationIndipendent.property(ObjectMapFile);
			
			datatable = new Datatable();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("there is an exception arised during execution of the method loadfiles, The execution:" + e);
		}

	}

	/*
	 * @Test public void c() { obrowser = Initialize.launch();
	 * Initialize.navigate(obrowser); LoginLogout.validateTitle(obrowser);
	 * LoginLogout.validateMyntraLogo(obrowser); LoginLogout.login(obrowser);
	 * LoginLogout.VerifyUser(obrowser); CartPage.addToBag(obrowser);
	 * CartPage.removeFromBag(obrowser); WishList.addToWishList(obrowser);
	 * WishList.moveWishListToBag(obrowser); WishList.placeOrder(obrowser);
	 * LoginLogout.Logout(obrowser);
	 * 
	 * }
	 */

	@Test
	public void executeTestScripts() {

		String StartTime = null, endtime = null;
		try {

			log.info("the execution of the method executeTestScripts strated here..");

			ReportUtil.startSuite("Scenarios");
			controllerFile = System.getProperty("user.dir") + "/Controller/data_Controller.xlsx";
			int controllerRowCount = datatable.rowCount(controllerFile, "Scenarios");
			for (int tcid = 0; tcid < controllerRowCount; tcid++) {

				String testcaseid = datatable.getCellData(controllerFile, "Scenarios", "TestCaseID", tcid + 2);
				String testcasename = datatable.getCellData(controllerFile, "Scenarios", "TestCaseName", tcid + 2);
				String description = datatable.getCellData(controllerFile, "Scenarios", "Description", tcid + 2);
				String runstatus = datatable.getCellData(controllerFile, "Scenarios", "RunStatus", tcid + 2);

				System.out.println("testcaseid :" + testcaseid);
				System.out.println("testcasename :" + testcasename);
				System.out.println("description :" + description);
				System.out.println("runstatus :" + runstatus);

				if (runstatus.equalsIgnoreCase("yes")) {
					log.info("The Scenario " + testcasename + " has started........");
					StartTime = ApplicationIndipendent.getDateTime("dd-MMM-yyyy h:m:s z");
					Class ODriver[] = new Class[1];
					ODriver[0] = WebDriver.class;

					obrowser = Initialize.launch();
					testScriptExcelFle = System.getProperty("user.dir") + "/TestScriptDataFiles/" + testcasename
							+ ".xlsx";
					int testScriptFileRowCount = datatable.rowCount(testScriptExcelFle, testcaseid);

					for (int tsid = 0; tsid < testScriptFileRowCount; tsid++) {
						String testscriptid = datatable.getCellData(testScriptExcelFle, testcaseid, "TestScriptID",
								tsid + 2);
						String testdescription = datatable.getCellData(testScriptExcelFle, testcaseid, "Description",
								tsid + 2);
						String methodname = datatable.getCellData(testScriptExcelFle, testcaseid, "MethodName",
								tsid + 2);
						String packageclassname = datatable.getCellData(testScriptExcelFle, testcaseid,
								"PackageClassName", tsid + 2);
						testDataColumn = datatable.getCellData(testScriptExcelFle, testcaseid, "TestDataColumn",
								tsid + 2);
						// verifytextdata=datatable.getCellData(testScriptExcelFile, testcaseid,
						// "VerifyText", tsid+2);
						// objectmapdata=datatable.getCellData(testScriptExcelFile, testcaseid,
						// "ObjectMap", tsid+2);
						System.out.println("testscriptid  :" + testscriptid);
						System.out.println("testdescription  :" + testdescription);
						System.out.println("methodname  :" + methodname);
						System.out.println("packageclassname  :" + packageclassname);
						System.out.println(" testDataColumn :" + testDataColumn);
						Class cls = Class.forName(packageclassname);
						Object obj = cls.newInstance();

						Method method = obj.getClass().getMethod(methodname, ODriver);
						log.info("The Method " + methodname + " execution has started in Scenario " + testcasename);
						String executionStatus = (String) method.invoke(obj, obrowser);

						testScriptStatus = testScriptStatus + executionStatus;
						String Screenshotpath = System.getProperty("user.dir") + "/Results/Screenshots";
						String screenshotName = Screenshotpath + "/Scenarios_" + testscriptid + "_" + testcasename + "_"
								+ methodname + "_Screenshot.jpeg";
						if (executionStatus.equalsIgnoreCase("fail")) {
							ApplicationIndipendent.captureScreenshot(obrowser, screenshotName);
						}
						log.info("The Execution status of the Method " + methodname + " in Scenario " + testcasename
								+ " has become " + executionStatus);
						ReportUtil.addArrayList(testscriptid, testdescription, methodname, packageclassname,
								executionStatus, screenshotName);
					}
					endtime = ApplicationIndipendent.getDateTime("dd-MMM-yyyy h:m:s z");
					log.info("The Scenario " + testcasename + " execution has ended ................");

					if (testScriptStatus.contains("Fail")) {
						ReportUtil.writeTestResults(testcaseid, testcasename, "Fail", StartTime, endtime);
					} else {
						ReportUtil.writeTestResults(testcaseid, testcasename, "Pass", StartTime, endtime);
					}
					testScriptStatus = "";
				}

				log.info("+++++++++++++++++++++++++++++++++++++++++");
			}
			log.info("The Execution of the Method executeTestScripts has ended here...");
		} catch (Exception e) {
			log.error(
					"there is an execption araised during the execution of the Method executeTestScripts , The Exception :" + e);
		}
		ReportUtil.endSuite();
	}

	@AfterSuite
	public void endScript() {
		String endTime = null;
		try {
			endTime = ApplicationIndipendent.getDateTime("dd-MMM-yyyy h:m:s z");
			ReportUtil.updateEndTime(endTime);
		} catch (Exception e) {
			log.error(
					"there is an execption araised during the execution of the Method endScript , The Exception :" + e);
		}
	}

}
