package testcases;

import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import utilities.DataProviders;

public class TC2_Loginddt extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = { "Datadriven" })
	public void testLogin(String email, String password, String expectedResult) {
		// 1) Navigate to the application URL
		String applicationUrl = properties.getProperty("appURL");
		driver.get(applicationUrl);

		// 2) Navigate to the Login page from the Home page
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();

		LoginPage loginPage = homePage.clickLogin();

		// 3) Perform login
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.clickLogin();

		// validate MyAccount Page is present or not
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean isOnMyAccountPage = myAccountPage.isMyAccountPageExists();

		// 4) Add assertions based on the Expected Result

		if (expectedResult.equalsIgnoreCase("Valid")) {
			if (isOnMyAccountPage) {
				myAccountPage.clickLogout();
				softAssert.assertTrue(true, "Login success as expected");
			} else {
				softAssert.fail("Login failed despite valid credentials");
			}
		} else if (expectedResult.equalsIgnoreCase("Invalid")) {
			if (isOnMyAccountPage) {
				myAccountPage.clickLogout();
				softAssert.fail("Login succeeded with invalid credentials.");
			} else {
				softAssert.assertTrue(true, "Login failed as expected");
			}
		}

	}

	@Test(dependsOnMethods = "testLogin")
	public void verifyAllCombinations() {
		// Validate results of all login attempts
		softAssert.assertAll();
	}

}
