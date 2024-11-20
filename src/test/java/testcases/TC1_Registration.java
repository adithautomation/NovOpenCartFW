package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegistrationPage;

/**
 * Test Case: Account Registration
 * 
 * Steps: 1) Navigate to application URL 
 * 2) Navigate to 'My Account' and click 'Register' 
 * 3) Fill in registration details 
 * 4) Agree to Privacy Policy and submit registration 
 * 5) Validate confirmation message
 */

public class TC1_Registration extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void testUserRegistration() {
		// 1) Navigate to application URL
		String applicationUrl = properties.getProperty("appURL");
		driver.get(applicationUrl);
		logger.info("Navigated to application URL:" + applicationUrl);

		// 2) Navigate to 'My Account' and click 'Register'
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount link.");

		RegistrationPage registrationPage = homePage.clickRegister();
		logger.info("Clicked on Register link, navigated to Registration page.");

		// 3) Fill in registration details
		logger.info("Entering user details for registration...");

		registrationPage.setFirstName(generateString().toUpperCase());
		logger.info("First Name set successfully...");

		registrationPage.setLastName(generateString().toUpperCase());
		logger.info("Last Name set successfully...");

		String userEmail = generateString() + "@gmail.com"; // abcdfef@gmail.com
		registrationPage.setEmail(userEmail);
		logger.info("Email entered:" + userEmail);

		String userPhoneNumber = generateNumber();
		registrationPage.setTelephone(userPhoneNumber);
		logger.info("Entered phone number:" + userPhoneNumber);

		String userPassword = generateAlphaNumeric();

		registrationPage.setPassword(userPassword);
		registrationPage.setConfirmPassword(userPassword);
		logger.info("Entered password and confirmed password successfully.");

		// 4) Agree to Privacy Policy and submit registration
		registrationPage.setPrivacyPolicy();
		logger.info("Agreed to Privacy Policy.");

		registrationPage.clickContinue();
		logger.info("Clicked 'Continue' to submit the registration form.");

		// 5) Validate confirmation message
		logger.info("Validating the confirmation message...");

		String confirmationMessage = registrationPage.getConfirmationMsg();
		Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!",
				"Registration failed: Confirmation message mismatch.");
		logger.info("Account registration successful. Confirmation message validated.");
	}

}
