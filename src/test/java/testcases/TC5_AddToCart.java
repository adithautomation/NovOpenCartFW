package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.ProductPage;
import pageobjects.SearchResultsPage;

/**
 * Test Case: Add Product to Cart
 * Steps:
 * 1. Navigate to application URL
 * 2. Enter an existing product name in the search text box
 * 3. Click the search button
 * 4. Verify the product appears in the search results
 * 5. Select the product from the search results
 * 6. Set the desired quantity
 * 7. Add the product to the cart
 * 8. Verify the success message
 */
public class TC5_AddToCart extends BaseClass {

    @Test(groups = { "Master" })
    public void testAddToCart() throws InterruptedException {
        // Step 1: Navigate to application URL
        String appURL = properties.getProperty("appURL");
        driver.get(appURL);
        logger.info("Navigated to application URL: " + appURL);

        // Initialize the HomePage
        HomePage homePage = new HomePage(driver);
        String productName = properties.getProperty("searchProductName");

        // Step 2: Enter the product name in the search text box
        homePage.enterProductName(productName);
        logger.info("Entered product name in the search box: " + productName);

        // Step 3: Perform the search
        SearchResultsPage searchResultsPage = homePage.clickSearch();
        logger.info("Clicked the Search button.");

        // Step 4: Verify the product exists in search results
        if (searchResultsPage.isProductExist(productName)) {
            logger.info("Product found in search results: " + productName);

            // Step 5: Select the product from the search results
            ProductPage productPage = searchResultsPage.selectProduct(productName);
            logger.info("Selected product: " + productName);

            // Step 6: Set the desired quantity
            productPage.setQuantity(properties.getProperty("productQuantity"));
            logger.info("Set quantity to: " + properties.getProperty("productQuantity"));

            // Step 7: Add the product to the cart
            productPage.addToCart();
            logger.info("Clicked 'Add to Cart' for product: " + productName);

            // Step 8: Verify the success message
            boolean isSuccessMessageDisplayed = productPage.checkConfMsg();
            Assert.assertTrue(isSuccessMessageDisplayed, "Success message not displayed!");
            logger.info("Success message verified successfully.");
        } else {
            logger.error("Product not found in search results: " + productName);
            Assert.fail("Product not found in search results.");
        }
    }
}
