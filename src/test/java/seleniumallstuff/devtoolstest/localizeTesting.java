package seleniumallstuff.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class localizeTesting {
    protected static ChromeDriver driver;
    String URL = "https://locations.dennys.com/search.html/";

    @BeforeClass
    public void testSetUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chrome_prefs = new HashMap<String, Object>();
        chrome_prefs.put("intl.accept_languages", "zh-CN");
        options.setExperimentalOption("prefs", chrome_prefs);
        driver = new ChromeDriver(options);

    }
        @Test
        public void test_Localize() throws InterruptedException {
            driver.navigate().to("https://www.google.com/");
            driver.manage().window().maximize();
            Thread.sleep(1000);

        }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    }

