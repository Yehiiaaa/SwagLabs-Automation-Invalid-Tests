import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {
    @Test(dataProvider = "Credentials")
    public void removingItemFromCartItemName(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.clickOnCartIcon();
        cartPage.removeItemFromCartItemName();
        productPage.resetAppState();
    }

    @Test(dataProvider = "Credentials")
    public void refreshCartPage(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.clickOnCartIcon();
        cartPage.checkCartItemsAfterRefresh();
        productPage.resetAppState();
    }

    @Test(priority = 1, dataProvider = "Credentials")
    public void resetAppCart(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.clickOnCartIcon();
        productPage.resetAppState();
        cartPage.checkCartItemsAfterReset();
    }
}
