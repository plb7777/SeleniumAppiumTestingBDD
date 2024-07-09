package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.Utility;

public class LoginApp extends Utility {

	public LoginApp() {
		PageFactory.initElements(getAndroidDriver(), this);
	}
	
	public void loginApp(String user, String pass) {
		WebElement username = scrollToDescription("username content-desc");
		WebElement password = scrollToDescription("password content-desc");
		username.sendKeys(user);
		password.sendKeys(pass);
		WebElement loginButton = scrollToText("Login");
		waitUntilElementClickable(loginButton);
		clickOnElement(loginButton);
	}
	
}
