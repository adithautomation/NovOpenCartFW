package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

/**
 * Test Case: Login with Valid Credentials
 * 
 * Steps: 
 1) Navigate to the application URL 
 2) Navigate to the Login page from the Home page 
 3) Perform login with valid credentials 
 4) Verify login by checking the presence of the 'My Account' page
 */

public class TC2_Login extends BaseClass {

	@Test(groups = { "Sanity", "Master" })
	public void testLoginWithValidCredentials() {

		// 1) Navigate to the application URL
		String applicationUrl = properties.getProperty("appURL");
		driver.get(applicationUrl);
		logger.info("Navigated to application URL: " + applicationUrl);

		// 2) Navigate to the Login page from the Home page
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on 'My Account' link.");

		LoginPage loginPage = homePage.clickLogin();
		logger.info("Clicked on 'Login' link, navigating to the Login Page.");

		// 3) Perform login with valid credentials
		logger.info("Entering user login credentials...");
		String userEmail = properties.getProperty("email");
		loginPage.setEmail(userEmail);
		logger.info("Entered email: " + userEmail);

		String userPassword = properties.getProperty("password");
		loginPage.setPassword(userPassword);
		logger.info("Entered password.");

		loginPage.clickLogin();
		logger.info("Clicked on the 'Login' button.");

		// 4) Verify login by checking the presence of the 'My Account' page
		MyAccountPage myAccountPage = new MyAccountPage(driver);

		logger.info("Verifying if the 'My Account' page is displayed...");
		boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageExists();

		Assert.assertTrue(isMyAccountPageDisplayed, "Login failed: MyAccount page not displayed");
		logger.info("Login successful. 'My Account' page is displayed.");
	}

}
