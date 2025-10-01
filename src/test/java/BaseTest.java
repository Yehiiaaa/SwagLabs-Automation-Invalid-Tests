import org.example.CartPage;
import org.example.CheckoutPage;
import org.example.LoginPage;
import org.example.ProductPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.CSVFileManager;
import utils.ConfigLoader;
import utils.ExcelFileManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CSVFileManager csvData;
    ExcelFileManager excelData;
    ConfigLoader configLoader;
    int i = 1;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        csvData = new CSVFileManager("src / main / resources / invalid_login_data.csv");
        excelData = new ExcelFileManager("src/main/resources/cashout_data.xlsx","Sheet1");
        configLoader = new ConfigLoader("src/main/resources/config.properties");
    }

    @BeforeMethod
    public void openURL() {
        driver.get(configLoader.getValue("url"));
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File directory = new File("./screenshots/");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getName() + i + ".png";
                FileHandler.copy(src, new File(screenshotPath));
                i++;
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @DataProvider (name = "CSVInvalidLoginData")
    public Object[][] getCSVLoginData() {
        CSVFileManager csv = new CSVFileManager("src/main/resources/invalid_login_data.csv");
        List<String[]> rows = csv.getRows();

        Object[][] data = new Object[rows.size()][2];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i)[0];
            data[i][1] = rows.get(i)[1];
        }
        return data;
    }
//    @DataProvider(name = "wrongLoginData")
//    public Object[][] wrongData() {
//        return new Object[][]{{"", "secret_sauce"}, {"standard_user", ""}, {"", ""},
//                {"test_user", "secret_sauce"}, {"standard_user", "123456"},
//                {"test_user", "123456"}, {"locked_out_user", "secret_sauce"},
//                {"locked_out_user", "123456"}, {"STANDARD_USER", "secret_sauce"},
//                {"standard_user", "SECRET_SAUCE"}, {"StandarD_USer", "secret_sauce"},
//                {"standard_user", "SECRet_sauCE"}, {"standard_user ", "secret_sauce"},
//                {"standard_user", "secret_sauce "}, {"' OR '1'='1", "secret_sauce"},
//                {"يحيى", "يحيى"}};
//    }

    @DataProvider(name = "Credentials")
    public Object[][] loginData() {
        return new Object[][]{{"standard_user", "secret_sauce"}};
    }

    @DataProvider(name = "longInputData")
    public Object[][] longInputData() {
        String longString = "s".repeat(101);
        return new Object[][]{{longString, "secret_sauce"}, {"standard_user", longString}};
    }

    @DataProvider(name = "ExcelData")
    public Object[][] getExcelData() {
        int rows = excelData.getRowCount();
        int cols = excelData.getColumnCount();
        System.out.print("Rows:"+rows);
        System.out.print("Cols:"+cols);

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = excelData.getSpecificCellValue(i, j);
            }
        }
        return data;
    }

//    @DataProvider(name = "wrongInformationData")
//    public Object[][] wrongInformation() {
//        String longString = "s".repeat(101);
//        return new Object[][]{{"standard_user", "secret_sauce", "", "", ""}, {"standard_user", "secret_sauce", "Yehia", "", ""},
//                {"standard_user", "secret_sauce", "Yehia", "Hussein", ""}, {"standard_user", "secret_sauce", "Y", "Hussein", "115200"},
//                {"standard_user", "secret_sauce", "Yehia", "H", "115200"}, {"standard_user", "secret_sauce", "Yehia!!@@#", "Hussein", "115200"},
//                {"standard_user", "secret_sauce", "Yehia", "Hussein!!@@#", "115200"}, {"standard_user", "secret_sauce", "      ", "Hussein", "115200"},
//                {"standard_user", "secret_sauce", "Yehia", "    ", "115200"}, {"standard_user", "secret_sauce", longString, "Hussein", "115200"},
//                {"standard_user", "secret_sauce", "Yehia", longString, "115200"}, {"standard_user", "secret_sauce", "Yehia1122", "Hussein", "115200"},
//                {"standard_user", "secret_sauce", "Yehia", "Hussein44", "115200"}, {"standard_user", "secret_sauce", "Yehia", "Hussein", "1152qmm"},
//                {"standard_user", "secret_sauce", "Yehia", "Hussein", "    "}, {"standard_user", "secret_sauce", "Yehia", "Hussein", "123##@!"},
//                {"standard_user", "secret_sauce", "Yehia", "Hussein", "12345678"}};
//    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


