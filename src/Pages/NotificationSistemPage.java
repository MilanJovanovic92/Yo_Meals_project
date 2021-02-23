package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	public NotificationSistemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getElementMessage() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));

	}

	public String getMessage() {
		return this.getElementMessage().getText();
	}

	public void notificationInvisible() {
		WebElement systemMessage = driver.findElement(By.xpath("//*[contains(@class, 'system_message')]"));
		wait.until(ExpectedConditions.attributeToBe(systemMessage, "style", "display: none;"));
	}

}
