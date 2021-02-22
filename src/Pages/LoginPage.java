package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getEmail() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmRegister']/div[2]/div[1]/fieldset/input"));
	}
	public WebElement getPassword() {
		return driver.findElement(By.xpath("//*[@id='frm_fat_id_frmRegister']/div[3]/div[1]/fieldset/input"));
	}

}
