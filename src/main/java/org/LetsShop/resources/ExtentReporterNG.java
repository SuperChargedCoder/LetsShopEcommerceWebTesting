package org.LetsShop.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	/*Using this class to setup the meta-data*/
	
	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\reports\\testReport.html";
		//ExtentReports, ExtentSparkReporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		/*Setting up the meta-data*/
		reporter.config().setReportName("Lets Shop Automation Result");
		reporter.config().setDocumentTitle("Lets Shop Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shubham");
		return extent;
	}
}