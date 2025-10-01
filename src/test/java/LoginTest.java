import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    @Test(priority = 1, dataProvider = "Credentials")
    public void login(String username, String password) {
        loginPage.enterCredentials(username, password);
        productPage.assertOnPageTitle("Products");
    }

    @Test(dependsOnMethods = "login", dataProvider = "CSVInvalidLoginData")
    public void loginInvalidTests(String username, String password) {
        loginPage.enterCredentials(username, password);
        loginPage.assertOnErrorMessage();
    }

    @Test(dependsOnMethods = "login", dataProvider = "longInputData")
    public void longDataInput(String username, String password) {
        loginPage.enterCredentials(username, password);
        loginPage.assertOnFieldSize();
    }
}
