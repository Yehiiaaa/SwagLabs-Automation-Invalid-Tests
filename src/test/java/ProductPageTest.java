import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    @Test( dataProvider = "Credentials")
    public void removingItemsFromCart(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.clickOnCartIcon();
        cartPage.removeItemFromCart();
        productPage.resetAppState();//Resetting app state to clear the cart and resets products
    }

    @Test(dataProvider = "Credentials")
    public void removeItemFromItemName(String username, String password) {
        loginPage.enterCredentials(username, password);
        productPage.removeItemFromName();
    }

    @Test(priority = 1, dataProvider = "Credentials")
    public void addingItemAndRefreshPage(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.refreshPage();
        productPage.assertItemsSelected();
        productPage.resetAppState();
    }

    @Test(dataProvider = "Credentials")
    public void resetAppStateDashboard(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.resetAppState();
        productPage.assertItemsSelectionStateAfterReset();
    }
}
