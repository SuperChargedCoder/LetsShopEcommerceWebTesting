package org.LetsShop.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.LetsShop.pageobjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	Properties prop;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		String filePath = "D:\\All Desktop folders\\Selenium Revision\\Shubham Selenium Codes\\LetsShopFramework\\src\\main\\java\\org\\LetsShop\\resources\\GlobalData.properties";
		FileInputStream file = new FileInputStream(filePath);
		prop.load(file);
		String Browser = prop.getProperty("browser");

		if (Browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public  void launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo(prop.getProperty("BaseURL"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);

		// String to HashMap- Jackson Datbind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		// {map, map}
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver; // Asking driver to take the Screenshot
		File source = ts.getScreenshotAs(OutputType.FILE); // Telling that we want to store the screenshot in file formate[Asking to take the screenshot as File formate]
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"); // 
		FileUtils.copyFile(source, file); // Saving the file at desired location
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
