package maven.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.pageObject.LaunchApplication;

public class BaseTest {

	public WebDriver driver;

	public LaunchApplication launchUrl;

	public WebDriver initializeDriver() throws IOException {

		// property class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\test\\resources\\GlobalData.properties");
		prop.load(fis);
		// String browserName = prop.getProperty("browser");

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");// this logic using from java ternary operator

		if (browserName.contains("chrome")) {

			ChromeOptions option = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",
					"C:/Automation tools\\Browser drivers\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440, 900));// full screen

		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Automation tools\\Browser drivers\\chromedriver_win32\\chromedriver_win32\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		else if (browserName.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver",
					"C:\\Automation tools\\Browser drivers\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	public List<HashMap<String, String>> getJsonToData(String filePath) throws IOException {

		// read json to string
		String jsonDatacontent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// read String to HashMap

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonDatacontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LaunchApplication landingpage() throws IOException {

		driver = initializeDriver();

		launchUrl = new LaunchApplication(driver);
		launchUrl.goTo();

		return launchUrl;
	}

	@AfterMethod
	public void tearDown() {

		driver.close();
	}
}
