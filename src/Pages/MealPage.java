package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	//Getters for elements
	public WebElement getQtyInput() {
		return driver.findElement(By.xpath("//*[@name='product_qty']"));
	}
	public WebElement getAddToCart() {
		return driver.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}
	public WebElement getAddToFavorite() {
		return driver.findElement(By.xpath("/html/body/div[6]/section[1]/div/div/div[1]/div[1]/a"));
	}
	
	//Cart and Favorite methods
	public void addMealToCart(String quantity) {
		this.getQtyInput().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		this.getQtyInput().sendKeys(quantity);
		js.executeScript("arguments[0].click();", this.getAddToCart());
	}
	public void addMealToFavorite() {
		js.executeScript("arguments[0].click();", this.getAddToFavorite());
	}

}
