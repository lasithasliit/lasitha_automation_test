package TaskOne;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestUIElementsTestCases {

    // Initialize the Webdriver
    private WebDriver driver;

    // Setup the Browser configuration
    @BeforeTest(alwaysRun = true)
    public void setUpBrowserConfiguration() {
        // Set Chrome Driver to the test suite
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTest\\chromedriver.exe");
        // Initialize the chrome driver
        driver = new ChromeDriver();
        // Navigate to the selected web page
        driver.get("https://magento.softwaretestingboard.com/");
        // Open the browser and maximize
        driver.manage().window().maximize();
    }

    // Test_Case_001 search Items and validate the results
    @Test(priority = 1)
    public void Test_Case_001_searchItemTest() {
        // Locate and interact with the search bar
        WebElement searchInput = driver.findElement(By.id("search"));
        // Send values and submit
        searchInput.sendKeys("example item");
        searchInput.submit();
        // Validate the web element values
        WebElement searchResults = driver.findElement(By.xpath("//*[@id=\"toolbar-amount\"]"));
        String searchResultsText = searchResults.getText();
        Assert.assertTrue(searchResultsText.contains("3 Items"), "Search results do not match the search term");
    }

    // Test_Case_002 Filter Search Results and validate the results
    @Test(priority = 2)
    public void Test_Case_002_filterSearchResultsTest() {
        // Click on "Tops" under Women category
        WebElement womenCategory = driver.findElement(By.linkText("Women"));
        womenCategory.click();
        WebElement topsCategory = driver.findElement(By.linkText("Tops"));
        topsCategory.click();

        // Select a value from the CATEGORY filter
        WebElement categoryFilter = driver.findElement(By.id("category-filter"));
        // Assuming "Blouses" is one of the options in the CATEGORY filter
        WebElement categoryOption = categoryFilter.findElement(By.xpath(".//option[contains(text(),'Blouses')]"));
        categoryOption.click();

        // Select a value from the COLOR filter
        WebElement colorFilter = driver.findElement(By.id("color-filter"));
        // Assuming "Black" is one of the options in the COLOR filter
        WebElement colorOption = colorFilter.findElement(By.xpath(".//option[contains(text(),'Black')]"));
        colorOption.click();

        // Apply the filters
        WebElement applyFiltersButton = driver.findElement(By.id("apply-filters"));
        applyFiltersButton.click();

        // Validate the filtered results
        WebElement filteredResults = driver.findElement(By.className("product-list"));
        // Assuming the filtered results have a specific class name or structure to identify them
        Assert.assertTrue(filteredResults.isDisplayed(), "Filtered results do not match the filter criteria");
    }

    // Test_Case_003 Add To Cart and validate the results
    @Test(priority = 3)
    public void Test_Case_003_addToCartTest() {
        // Select an item from the Gear section
        WebElement gearSection = driver.findElement(By.linkText("Gear"));
        gearSection.click();

        // Click on a specific item
        WebElement item = driver.findElement(By.xpath("//*[@id=\"ui-id-25\"]/span"));
        item.click();

        // Add the item to the cart
        WebElement addToCartButton = driver.findElement(By.id("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/div/div[3]/div/div[1]/form/button"));
        addToCartButton.click();

        // Validate that the item has been added to the cart
        WebElement cart = driver.findElement(By.id("cart"));
        String cartItemCount = cart.getText();
        Assert.assertEquals(cartItemCount, "1", "Item has not been added to the cart");
    }

    // Quite from the Browser
    @AfterTest
    public void quitFromTestSuite() {
        // Delete all cookies and quit
        driver.manage().deleteAllCookies();
        // Quit from the browser
        driver.quit();
    }
}
