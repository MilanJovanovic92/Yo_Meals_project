package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getSelectLocation() {
		return driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[1]/div/a"));
	}

	public WebElement getPopupClose() {
		return driver.findElement(By.xpath("//*[@id='location-popup']/div/div/div/div/a"));
	}

	// Location methods
	public WebElement getKeyword() {
		return driver.findElement(By.id("locality_keyword"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.id("location_id"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.name("btn_submit"));
	}

	// Popup location
	public void openPopup() {
		this.getSelectLocation().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();

		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");

		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), dataValue);
		js.executeScript("arguments[0].click()", this.getSubmit());

	}

	public void closePopup() {
		this.getPopupClose().click();
	}

}
