package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	// Getters for basic informations
	public WebElement getFirstName() {
		return driver.findElement(By.xpath("//*[@name='user_first_name']"));
	}

	public WebElement getLastName() {
		return driver.findElement(By.xpath("//*[@name='user_last_name']"));
	}

	public WebElement getAddress() {
		return driver.findElement(By.xpath("//*[@name='user_address']"));
	}

	public WebElement getPhoneNo() {
		return driver.findElement(By.xpath("//*[@name='user_phone']"));
	}

	public WebElement getZipCode() {
		return driver.findElement(By.xpath("//*[@name='user_zip']"));
	}

	public void getCountry(String country) throws InterruptedException {
		WebElement countryElement = driver.findElement(By.xpath("//*[@id=\"user_country_id\"]"));
		Select countrySelect = new Select(countryElement);
		countrySelect.selectByVisibleText(country);
		Thread.sleep(2000);
	}

	public void getState(String state) throws InterruptedException {
		WebElement stateElement = driver.findElement(By.xpath("//*[@id=\"user_state_id\"]"));
		Select stateSelect = new Select(stateElement);
		stateSelect.selectByVisibleText(state);
		Thread.sleep(2000);
	}

	public void getCity(String city) throws InterruptedException {
		WebElement cityElement = driver.findElement(By.xpath("//*[@id=\"user_city\"]"));
		Select citySelect = new Select(cityElement);
		citySelect.selectByVisibleText(city);
		Thread.sleep(2000);
	}

	public WebElement getInfoSaveButton() {
		return driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}

	// Profile picture
	public WebElement getUploadImage() throws InterruptedException {
		return driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div/div/div[2]/div/div[1]/div/a"));

	}

	public void uploadImage(String image) throws InterruptedException {

		js.executeScript("arguments[0].click();", this.getUploadImage());
		Thread.sleep(3000);
		WebElement input = driver.findElement(By.xpath("//*[@id=\"form-upload\"]/input"));
		input.sendKeys(image);
	}

	public void deleteImage() {
		WebElement delete = driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
		js.executeScript("arguments[0].click();", delete);
	}

	// Method for change informations
	public void updateProfile(String firstName, String lastName, String address, String phoneNo, String zipCode,
			String country, String state, String city) throws InterruptedException {

		this.getFirstName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getLastName().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getAddress().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getPhoneNo().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getZipCode().sendKeys(Keys.CONTROL + "a", Keys.DELETE);

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhoneNo().sendKeys(phoneNo);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry(country);
		this.getState(state);
		this.getCity(city);

		this.getInfoSaveButton().click();
	}
}