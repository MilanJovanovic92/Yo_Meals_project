package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.AuthPage;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSistemPage;
import Pages.ProfilePage;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {

		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		// Import Pages
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		NotificationSistemPage notificationSystem = new NotificationSistemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);

		// Add meal to cart
		locationPopup.closePopup();
		meal.addMealToCart("3");

		Assert.assertTrue(notificationSystem.getMessage().contains("The Following Errors Occurred:"),
				"[ERROR]: Error message was not displayed.");
		Assert.assertTrue(notificationSystem.getMessage().contains("Please Select Location"),
				"[ERROR]: Please Select Location message was not displayed.");

		notificationSystem.notificationInvisible();

		// Set location
		locationPopup.openPopup();
		locationPopup.setLocation("City Center - Albany");
		Thread.sleep(500);

		// Add meal to cart

		meal.addMealToCart("3");

		Assert.assertTrue(notificationSystem.getMessage().contains("Meal Added To Cart"),
				"[ERROR]: Meal Added message was not displayed.");
	}

	@Test(priority = 2)
	public void addMealToFavoriteTest() throws InterruptedException {
		Thread.sleep(3000);
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		// Import Pages
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSistemPage notificationSystem = new NotificationSistemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);

		// Add meal to favorite
		locationPopup.closePopup();
		Thread.sleep(3000);
		meal.addMealToFavorite();

		Assert.assertTrue(notificationSystem.getMessage().contains("Please login first!"),
				"[ERROR]:Please login message was not displayed.");
		notificationSystem.notificationInvisible();
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");

		// Login and add meal to favorite
		login.login(email, password);

		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		meal.addMealToFavorite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystem.getMessage().contains("Product has been added to your favorites."),
				"[ERROR]:Product added message was not displayed.");

	}

	@Test(priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {
		Thread.sleep(3000);
		this.driver.navigate().to(this.baseUrl + "/meals");

		// Import Pages
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		NotificationSistemPage notificationSystem = new NotificationSistemPage(driver, wait, js);
		MealPage meal = new MealPage(driver, wait, js);
		CartSummaryPage cartSummary = new CartSummaryPage(driver, wait, js);

		SoftAssert sa = new SoftAssert();

		locationPopup.setLocation("City Center - Albany");

		// Import xlsx file
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");

		// Add meal to cart
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);

			XSSFCell meals = row.getCell(0);
			String links = meals.getStringCellValue();

			this.driver.navigate().to(links);
			Thread.sleep(3000);
			meal.addMealToCart("1");

			sa.assertTrue(notificationSystem.getMessage().contains("Meal Added To Cart"),
					"[ERROR]:Meal added message was not displayed.");
			notificationSystem.notificationInvisible();
		}
		sa.assertAll();

		// Clear
		cartSummary.clearAll();

		Assert.assertTrue(notificationSystem.getMessage().contains("All meals removed from Cart successfully"),
				"[ERROR]: Meals removed message was not displayed.");

		workbook.close();
		fis.close();

	}
}
