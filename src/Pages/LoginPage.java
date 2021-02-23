package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}

	public WebElement getEmail() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[1]/input"));
	}

	public WebElement getPassword() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[2]/input"));
	}

	public WebElement getLoginSubmit() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[4]/input"));
	}

	// Login
	public void login(String email, String password) {
		this.getEmail().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getPassword().sendKeys(Keys.CONTROL + "a", Keys.DELETE);

		this.getEmail().sendKeys(email);
		this.getPassword().sendKeys(password);
		this.getLoginSubmit().click();

	}
}
