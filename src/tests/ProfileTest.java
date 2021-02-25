package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AuthPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.NotificationSistemPage;
import Pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		// Import Pages
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSistemPage notificationSystem = new NotificationSistemPage(driver, wait, js);
		ProfilePage profile = new ProfilePage(driver, wait, js);
		AuthPage auth = new AuthPage(driver, wait, js);

		// Login
		locationPopup.closePopup();
		login.login(email, password);
		Assert.assertTrue(notificationSystem.getMessage().contains("Login Successfull"),
				"[ERROR]: Login message was not displayed.");

		// Update Profile
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		profile.updateProfile("Will", "W", "Serbia", "056482456", "12500", "United Kingdom", "Cambridge", "Cambridge");
		Assert.assertTrue(notificationSystem.getMessage().contains("Setup Successful"),
				"[ERROR]: Setup successful message was not displayed.");
		notificationSystem.notificationInvisible();

		// Logout
		auth.logout();
		Assert.assertTrue(notificationSystem.getMessage().contains("Logout Successfull!"),
				"[ERROR]: Logout message was not displayed.");

	}

	@Test(priority = 5)
	public void changeProfileImageTest() throws InterruptedException, IOException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");

		// Import Pages
		LocationPopupPage locationPopup = new LocationPopupPage(driver, wait, js);
		LoginPage login = new LoginPage(driver, wait, js);
		NotificationSistemPage notificationSystem = new NotificationSistemPage(driver, wait, js);
		ProfilePage profile = new ProfilePage(driver, wait, js);
		AuthPage auth = new AuthPage(driver, wait, js);

		// Login
		locationPopup.closePopup();
		login.login(email, password);
		Assert.assertTrue(notificationSystem.getMessage().contains("Login Successfull"),
				"[ERROR]: Login message was not displayed.");

		// Upload profile image
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		Thread.sleep(3000);
		String imgPath = new File("images/slika.png").getCanonicalPath();

		profile.uploadImage(imgPath);
		Assert.assertTrue(notificationSystem.getMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR]: Upload successfully message was not displayed.");
		notificationSystem.notificationInvisible();

		// Delete image
		profile.deleteImage();
		Assert.assertTrue(notificationSystem.getMessage().contains("Profile Image Deleted Successfully"),
				"[ERROR]: Image Deleted message was not displayed.");

		notificationSystem.notificationInvisible();

		// Logout
		auth.logout();
		Assert.assertTrue(notificationSystem.getMessage().contains("Logout Successfull!"),
				"[ERROR]: Logout message was not displayed.");
	}

}
