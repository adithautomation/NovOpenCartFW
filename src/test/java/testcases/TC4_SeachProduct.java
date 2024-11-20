package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.SearchResultsPage;

/**
 * Test Case: Product Search
 * 
 * Steps:
 * 1) Navigate to the application URL
 * 2) Navigate to the Home page and initiate product search
 * 3) Enter the product name in the search field
 * 4) Click on the search button
 * 5) Verify if the product is displayed in the search results
 */
public class TC4_SeachProduct extends BaseClass {

    @Test(groups = {"Master"})
    public void testProductSearch() throws InterruptedException {
        // Step 1: Navigate to the application URL
        String applicationUrl = properties.getProperty("appURL");
        driver.get(applicationUrl);
        logger.info("Navigated to application URL: " + applicationUrl);

        // Step 2: Navigate to the Home page and initiate product search
        HomePage homePage = new HomePage(driver);
        logger.info("Navigated to the Home page.");

        // Step 3: Enter the product name in the search field
        String productToSearch = "mac"; // Could be parameterized as needed
        homePage.enterProductName(productToSearch);
        logger.info("Entered product name in search field: " + productToSearch);

        // Step 4: Click on the search button
        SearchResultsPage searchResultsPage=homePage.clickSearch();
        logger.info("Clicked on the 'Search' button to initiate product search.");

        // Step 5: Verify if the product is displayed in the search results
        Assert.assertTrue(searchResultsPage.isSearchResultsPageExists());
        logger.info("Navigated to the Search Results page.");

        // Check if the specific product exists in the search results
        String expectedProductName = "MacBook";
        boolean isProductDisplayed = searchResultsPage.isProductExist(expectedProductName);
        logger.info("Verifying if the product '" + expectedProductName + "' is displayed in the search results.");

        // Assertion to verify that the product exists in the search results
        Assert.assertTrue(isProductDisplayed, "Product '" + expectedProductName + "' not found in search results.");
        logger.info("Product '" + expectedProductName + "' found in search results. Test passed.");
    }
}
