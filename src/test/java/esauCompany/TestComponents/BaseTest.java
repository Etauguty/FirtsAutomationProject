package esauCompany.TestComponents;

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

import esauCompany.PageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver; 
	public LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException {
		//Getting the properties from the GlobalData.properpies file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//esauCompany//Resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {			
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\esau_\\Documents\\chromedriver.exe");
				/*if(browserName.contains("headless"))
					{
					options.addArguments("headless");
					}*/
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//run in full screen
			}
			else if(browserName.contains("edge"))
			{
				System.setProperty("webdriver.edge.driver", "C:\\Users\\esau_\\Documents\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			else if(browserName.contains("firefox"))
			{
				System.setProperty("webdriver.edge.driver", "C:\\Users\\esau_\\Documents\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
			driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//Read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//String to HashMap -Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			});
		
		return data;
		//{map, map}
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";
	}
}
