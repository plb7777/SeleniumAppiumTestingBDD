package steps;

import org.junit.Assert;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginApp;
import utils.Utility;

public class AppLogin {
	
	private Base base;
	private Utility util;

	@Given("init mobile app")
	public void init_mobile_app() {
		base = new Base();
		util = new Utility();
		base.initAndroidDriver();
	}

	@When("login with valid username and password")
	public void login_with_valid_username_and_password() {
		LoginApp login = new LoginApp();
		String user = "test";
		String pass = "pass123";
		login.loginApp(user, pass);
	}

	@Then("check for home page")
	public void check_for_home_page() {
		HomePage homepage = new HomePage();
		util.waitUntilElementVisible(homepage.getLogoImage());
		Assert.assertTrue(homepage.getLogoImage().isDisplayed());
		util.sleep(2);
		base.quitAndroidDriver();
	}

}
