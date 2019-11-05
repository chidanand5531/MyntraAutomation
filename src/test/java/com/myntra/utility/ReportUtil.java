
package com.myntra.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

public class ReportUtil {

	public static String ResultReportName;
	public static String ResultFoderPath;
	public static String ScenarioName;
	public static ArrayList<String> testscriptid = new ArrayList<String>();
	public static ArrayList<String> testdescription = new ArrayList<String>();
	public static ArrayList<String> methodname = new ArrayList<String>();
	public static ArrayList<String> pkgclassname = new ArrayList<String>();
	public static ArrayList<String> teststatus = new ArrayList<String>();
	public static ArrayList<String> screenshotname = new ArrayList<String>();

	public static void createReport(String FileName, String teststarttime, String environment) {
		BufferedWriter bw = null;
		try {
			ResultReportName = FileName;
			ResultFoderPath = ResultReportName.substring(0, ResultReportName.lastIndexOf("/"));
			String configFile = System.getProperty("user.dir") + "/Configuration/configuration.properties";
			Properties config = ApplicationIndipendent.property(configFile);
			String BrowserName = config.getProperty("browsername");
			String applicationname = config.getProperty("applicationname");
			String appversion = config.getProperty("applicationversion");
			String url = config.getProperty("url");
			bw = new BufferedWriter(new FileWriter(ResultReportName));
			bw.write("<html>");
			bw.write("<head><title>Myntra Automation Results</title></head>");
			bw.write("<body>");
			bw.newLine();
			bw.write("<h1 align=center>Myntra Automation Results</h1>");
			bw.write("<table border=2>");
			bw.newLine();
			bw.write("<h3><u>Automation Summary</u></h3>");
			bw.write("<tr>");
			bw.write("<th width=150 align=center>Item Name</th>");
			bw.write("<th width=250 align=center>Item Value</th>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >Application Name</td>");
			bw.write("<td width=250 >" + applicationname + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >Application Version</td>");
			bw.write("<td width=250 >" + appversion + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >Browser Name</td>");
			bw.write("<td width=250 >" + BrowserName + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >URL</td>");
			bw.write("<td width=250 >" + url + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >Environment</td>");
			bw.write("<td width=250>" + environment + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >Start Time</td>");
			bw.write("<td width=250 >" + teststarttime + "</td>");
			bw.write("</tr>");
			bw.write("<tr>");
			bw.write("<td width=150 >End Time</td>");
			bw.write("<td width=250 >END_TIME</td>");
			bw.write("</tr>");

			bw.newLine();
			bw.write("</table>");
			bw.newLine();
			bw.write("</body>");
			bw.write("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void startSuite(String ScenarioName1) {
		BufferedWriter bw = null;
		try {
			ScenarioName = ScenarioName1.replaceAll(" ", "_");
			bw = new BufferedWriter(new FileWriter(ResultReportName, true));
			bw.write("<html>");
			bw.write("<body>");
			bw.write("<table border=2 width=100%>");
			bw.write("<h3><u>TestScript Detail Results</u></h3>");
			bw.write("<tr>");
			bw.write("<th width=15% align=center>Testcase ID</th>");
			bw.write("<th width=20% align=center>Testcase Name</th>");
			bw.write("<th width=15% align=center>Status</th>");
			bw.write("<th width=25% align=center>Test Start Time</th>");
			bw.write("<th width=25% align=center>Test End Time</th>");

			bw.write("</tr>");
			bw.write("</body>");
			bw.write("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void endSuite() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(ResultReportName, true));
			bw.write("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void addArrayList(String tsid, String desc, String methodname1, String pkgclass, String status,
			String screenshot) {
		try {
			testscriptid.add(tsid);
			testdescription.add(desc);
			methodname.add(methodname1);
			pkgclassname.add(pkgclass);
			teststatus.add(status);
			screenshotname.add(screenshot);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeTestResults(String testcaseid, String testcasename, String status, String teststarttime,
			String testendtime) {
		String FileName = null;
		BufferedWriter bw = null;

		try {
			FileName = ResultFoderPath + "/" + ScenarioName + "_" + testcasename + "_Detail_Results.html";
			bw = new BufferedWriter(new FileWriter(FileName));
			bw.write("<html>");
			bw.write("<head><title>" + testcasename + " Detail Results</title></head>");
			bw.write("<body>");
			bw.write("<h1 align=center>" + testcasename + " Detail Results</h1>");
			bw.write("<table border=2 width=100%>");
			bw.write("<h3><u>" + testcasename + " Detail Results</u></h3>");
			bw.write("<tr>");
			bw.write("<th align=center width=15%>TestScriptID</th>");
			bw.write("<th align=center width=20%>Description</th>");
			bw.write("<th align=center width=15%>MethodName</th>");
			bw.write("<th align=center width=20%>PackageName</th>");
			bw.write("<th align=center width=15%>Status</th>");
			bw.write("<th align=center width=15%>ScreenshotPath</th>");
			bw.write("</tr>");
			if (methodname != null) {
				for (int i = 0; i < methodname.size(); i++) {
					bw.write("<tr>");
					bw.write("<td align=center width=15%>" + testscriptid.get(i) + "</td>");
					bw.write("<td align=center width=15%>" + testdescription.get(i) + "</td>");
					bw.write("<td align=center width=15%>" + methodname.get(i) + "</td>");
					bw.write("<td align=center width=15%>" + pkgclassname.get(i) + "</td>");
					if (teststatus.get(i).equalsIgnoreCase("pass")) {
						bw.write("<td align=center width=15%>" + teststatus.get(i) + "</td>");
						bw.write("<td align=center width=15%>&nbsp</td>");
					} else {
						bw.write("<td align=center width=15%>" + teststatus.get(i) + "</td>");
						bw.write("<td align=center width=15%><a href=file:///" + screenshotname.get(i)
								+ ">ScreenshotPath</a></td>");
					}

					bw.write("</tr>");
				}
			}
			bw.write("</table>");
			bw.write("</body>");
			bw.write("</html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter(ResultReportName, true));
			bw.write("<tr>");
			bw.write("<td width=15% align=center>" + testcaseid + "</td>");
			bw.write("<td width=20% align=center>" + testcasename + "</td>");
			bw.write("<td width=15% align=center><a href=file:///" + FileName + ">" + status + "</a></td>");
			bw.write("<td width=25% align=center>" + teststarttime + "</td>");
			bw.write("<td width=25% align=center>" + testendtime + "</td>");
			bw.write("</tr>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		testscriptid = new ArrayList<String>();
		testdescription = new ArrayList<String>();
		methodname = new ArrayList<String>();
		pkgclassname = new ArrayList<String>();
		teststatus = new ArrayList<String>();
		screenshotname = new ArrayList<String>();

	}

	public static void updateEndTime(String endTime) {
		try {
			StringBuffer str = new StringBuffer();
			FileInputStream fin = new FileInputStream(ResultReportName);

			DataInputStream dis = new DataInputStream(fin);
			InputStreamReader fr = new InputStreamReader(dis);
			BufferedReader br = new BufferedReader(fr);
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				if (strLine.indexOf("END_TIME") != -1) {
					strLine = strLine.replace("END_TIME", endTime);
				}
				str.append(strLine);
			}
			fin.close();
			FileOutputStream fout = new FileOutputStream(ResultReportName);
			DataOutputStream out = new DataOutputStream(fout);
			out.writeBytes(str.toString());

			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}