package base;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Utility;

public class Base {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static Actions action;
	private static JavascriptExecutor jse;
	
	public void initWebDriver() {
		Utility util = new Utility();
		String browserName = util.getValue("browserName");
		int timeOut = Integer.parseInt(util.getValue("timeOut"));
		boolean isHeadless = Boolean.parseBoolean(util.getValue("isHeadless"));
		
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--disable-gpu");
			if (isHeadless) {
				opt.addArguments("--window-size=1920x1080");
				opt.addArguments("--headless");
				driver = new ChromeDriver(opt);
			} else {
				driver = new ChromeDriver(opt);
				driver.manage().window().maximize();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions opt = new FirefoxOptions();
			opt.addArguments("--disable-gpu");
			if (isHeadless) {
				opt.addArguments("--window-size=1920x1080");
				opt.addArguments("--headless");
				driver = new FirefoxDriver(opt);
			} else {
				driver = new FirefoxDriver(opt);
				driver.manage().window().maximize();
			}
		} else {
			util.print(browserName + " not supported yet");
			return;
		}
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			action = new Actions(driver);
			jse = (JavascriptExecutor) driver;
		}
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public WebDriverWait getWebDriverWait() {
		return wait;
	}
	
	public Actions getActions() {
		return action;
	}
	
	public JavascriptExecutor getJavascriptExecutor() {
		return jse;
	}
	
	public void closeWebDriver() {
		if (driver != null) {
			driver.close();
		}
	}
	
	public void quitWebDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	private static AndroidDriver _driver;
	
	public void initAndroidDriver() {
		Utility util = new Utility();
		int timeOut = Integer.parseInt(util.getValue("timeOut"));
		
		try {
			_driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), setCapabilities());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (_driver != null) {
			_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			action = new Actions(_driver);
			jse = (JavascriptExecutor) _driver;
		}		
	}
	
	private Capabilities setCapabilities() {
		Utility util = new Utility();
		String platformName = util.getValue("platformName");
		String deviceName = util.getValue("deviceName");
		String automationName = util.getValue("automationName");
		int newCommandTimeout = Integer.parseInt(util.getValue("newCommandTimeout"));
		String appPackage = util.getValue("appPackage");
		String appActivity = util.getValue("appActivity");
		boolean autoGrantPermissions = Boolean.parseBoolean(util.getValue("autoGrantPermissions"));
		String apkPath = util.getValue("apkPath");
		String apkName = util.getValue("apkName");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", platformName);
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("automationName", automationName);
		cap.setCapability("newCommandTimeout", newCommandTimeout);
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		cap.setCapability("autoGrantPermissions", autoGrantPermissions);
		cap.setCapability("app", apkPath + apkName);
		return cap;
	}
	
	public WebDriver getAndroidDriver() {
		return _driver;
	}
	
	public void closeAndroidDriver() {
		if (_driver != null) {
			_driver.close();
		}
	}
	
	public void quitAndroidDriver() {
		if (_driver != null) {
			_driver.quit();
		}
	}
	
}
