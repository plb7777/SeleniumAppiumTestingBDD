package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import base.Base;
import io.appium.java_client.AppiumBy;

public class Utility extends Base {

	private String configFile = System.getProperty("user.dir") + "//src//main//resources//properties//config.properties";
	
	
	public String getValue(String key) {
		Properties pObj = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configFile);
			pObj.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pObj.getProperty(key);
	}

	private Alert alert;

	public void print(Object obj) {
		try {
			ExtentCucumberAdapter.addTestStepLog(obj.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(obj);
	}

	public void clickOnElement(WebElement element) {
		element.click();
	}

	public void fillData(WebElement element, String data) {
		element.sendKeys(data);
	}

	public void selectDropdownByText(WebElement element, String text) {
		new Select(element).selectByVisibleText(text);
	}

	public void waitUntilAlertVisible() {
		try {
			getWebDriverWait().until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			
		}
	}

	public boolean isAlertVisible() {
		try {
			alert = getWebDriver().switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Alert getAlert() {
		return alert;
	}
	
	public void waitUntilElementVisible(WebElement element) {
		try {
			getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			
		}
	}
	
	public void waitUntilElementClickable(WebElement element) {
		try {
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			
		}
	}
	
	public void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void moveToElement(WebElement element) {
		getActions().moveToElement(element).perform();
	}
	
	public void scrollToElement(WebElement element) {
		getActions().scrollToElement(element).perform();
	}
	
	public void scrollByAmount(int x, int y) {
		getActions().scrollByAmount(x, y).perform();
	}

	public byte[] getScreenshot() {
		byte[] b = null;
		if (getWebDriver() != null) {
			b = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
			return b;
		} else if (getAndroidDriver() != null) {
			b = ((TakesScreenshot) getAndroidDriver()).getScreenshotAs(OutputType.BYTES);
			return b;
		} else {
			return b;
		}		
	}
	
	// appium 
	
	public WebElement scrollToText(String text) {
		return getAndroidDriver().findElement(new AppiumBy.ByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0));"));
	}
	
	public WebElement scrollToDescription(String desc) {
		return getAndroidDriver().findElement(new AppiumBy.ByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scollDescriptionIntoView(\"" + desc + "\");"));
	}

}
