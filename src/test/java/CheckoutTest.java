import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    @Test(dataProvider = "Credentials")
    public void emptyCartCashout(String username, String password) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.clickOnCartIcon();
        checkoutPage.clickOnCheckoutButton();
        checkoutPage.assertEmptyCartCheckout();
        productPage.resetAppState();
    }

    @Test(dataProvider = "ExcelData")
    public void checkoutInvalidTests(String username, String password, String firstName, String lastName, String postalCode) throws InterruptedException {
        loginPage.enterCredentials(username, password);
        productPage.selectProducts();
        productPage.clickOnCartIcon();
        checkoutPage.clickOnCheckoutButton();
        checkoutPage.yourInformationDataFill(firstName, lastName, postalCode);
        checkoutPage.assertOnErrorMessage();
    }
}