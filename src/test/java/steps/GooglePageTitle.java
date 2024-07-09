package steps;

import org.junit.Assert;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.Utility;

public class GooglePageTitle {

	private Base base;
	private Utility util;

	@Given("init browser")
	public void init_browser() {
		base = new Base();
		util = new Utility();
		base.initWebDriver();
	}

	@When("browse to url")
	public void browse_to_url() {
		String url = util.getValue("url");
		base.getWebDriver().get(url);
	}

	@Then("get homepage title")
	public void get_homepage_title() {
		String title = base.getWebDriver().getTitle();
		util.print("Page Title: " + title);
	}

	@Then("search query in google")
	public void search_query_in_google() {
		HomePage homepage = new HomePage();
		util.waitUntilElementVisible(homepage.getLogoImage());
		Assert.assertTrue("Google image logo is not displayed", homepage.getLogoImage().isDisplayed());
		// modify above assert condition according to need
		int len = homepage.getSearchButtons().size();
		int k = 0;
		for (int i = 0; i < len; i++) {
			if (homepage.getSearchButtons().get(i).isDisplayed()) {
				k = i;
			}
		}
		util.moveToElement(homepage.getSearchButtons().get(k));
		String cursorVal = homepage.getSearchButtons().get(k).getCssValue("cursor");
		util.print(cursorVal);
		Assert.assertEquals(cursorVal, "pointer");
		homepage.searchQuery("appium driver");
		util.sleep(2);
		base.quitWebDriver();
	}

}
