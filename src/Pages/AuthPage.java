package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);

	}

	public WebElement getUser() {
		return driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccount() {
		return driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[1]/a"));
	}

	public WebElement getLogout() {
		return driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[2]/a"));
	}

	// Logout
	public void logout() {
		js.executeScript("arguments[0].click();", this.getUser());
		wait.until(ExpectedConditions.elementToBeClickable(getLogout()));
		js.executeScript("arguments[0].click();", this.getLogout());
	}

}
