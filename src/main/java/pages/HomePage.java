package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utility;

public class HomePage extends Utility {
	
	@FindBy (xpath = "//img[@class='lnXdpd']")
	private WebElement logoImage;
	
	@FindBy (xpath = "//textarea[@class='gLFyf']")
	private WebElement searchTextbox;
	
	@FindBy (xpath = "//*[@value='Google Search']")
	private List<WebElement> searchButtons;

	public HomePage() {
		PageFactory.initElements(getWebDriver(), this);
	}

	public WebElement getLogoImage() {
		return logoImage;
	}

	public WebElement getSearchTextbox() {
		return searchTextbox;
	}

	public void setSearchTextbox(WebElement searchTextbox) {
		this.searchTextbox = searchTextbox;
	}
	
	public List<WebElement> getSearchButtons() {
		return searchButtons;
	}
	
	// methods
	
	public void searchQuery(String query) {
		waitUntilElementClickable(searchTextbox);
		fillData(searchTextbox, query);
		searchTextbox.sendKeys(Keys.ENTER);
	}
	
}
