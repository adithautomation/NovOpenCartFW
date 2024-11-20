package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.LogoutPage;
import pageobjects.MyAccountPage;

/**
 * Test Case: User Logout
 * 
 * Steps:
 * 1) Navigate to the application URL
 * 2) Navigate to the Login page from the Home page
 * 3) Perform login with valid credentials
 * 4) Verify login by checking the presence of the 'My Account' page
 * 5) Click on Logout link
 * 6) Click on Continue button
 * 7) Verify navigation back to the Home Page
 */
public class TC3_Logout extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void testLogout() {
      
        // Step 1: Navigate to the application URL
        String applicationUrl = properties.getProperty("appURL");
        driver.get(applicationUrl);
        logger.info("Navigated to application URL: " + applicationUrl);

        // Step 2: Navigate to the Login page from the Home page
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on 'My Account' link.");

        LoginPage loginPage = homePage.clickLogin();
        logger.info("Clicked on 'Login' link, navigating to the Login Page.");

        // Step 3: Perform login with valid credentials
        String userEmail = properties.getProperty("email");
        String userPassword = properties.getProperty("password");
        logger.info("Entering login credentials...");
        loginPage.setEmail(userEmail);
        logger.info("Entered email: " + userEmail);

        loginPage.setPassword(userPassword);
        logger.info("Entered password.");

        loginPage.clickLogin();
        logger.info("Clicked on the 'Login' button.");

        // Step 4: Verify login by checking the presence of the 'My Account' page
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        logger.info("Checking if 'My Account' page is displayed...");
        boolean isMyAccountPageVisible = myAccountPage.isMyAccountPageExists();
        softAssert.assertTrue(isMyAccountPageVisible, "Login failed: 'My Account' page is not displayed.");
        logger.info(isMyAccountPageVisible? "Login successful. 'My Account' page is visible."
                : "Login verification failed.");

        // Step 5: Click on Logout link
        logger.info("Logging out from the account...");
        LogoutPage logoutPage = myAccountPage.clickLogout();
        logger.info("Clicked on 'Logout' link.");

        // Step 6: Click on Continue button
        logger.info("Clicking on 'Continue' button to navigate back to the Home Page...");
        HomePage postLogoutHomePage = logoutPage.clickContinue();

        // Step 7: Verify navigation back to the Home Page
        logger.info("Checking if the Home Page is displayed after logout...");
        boolean isHomePageVisible = postLogoutHomePage.isHomePageExists();
        softAssert.assertTrue(isHomePageVisible, "Logout failed: Home Page is not displayed.");
        logger.info(isHomePageVisible? "Logout successful. Navigated back to the Home Page."
                : "Logout verification failed.");

        // Assert all to validate all soft assertions
        softAssert.assertAll();
    }
}
