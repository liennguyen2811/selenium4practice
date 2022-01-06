package seleniumallstuff.devtoolstest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class GeoLocationTest {
    protected static ChromeDriver driver;
    String URL = "https://locations.dennys.com/search.html/";

    @BeforeClass
    public void testSetUp() {
        WebDriverManager.chromedriver().setup();
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
       // driver = new ChromeDriver(options);
        driver = new ChromeDriver();

    }

    @Test
    public void test_Selenium4_Geolocation() throws InterruptedException {
        Map< String,Object> coordinates =
                new HashMap< String,Object>();

        /* Create a hashmap for latitude, longitude, and accuracy as needed by Google Maps */
//        coordinates.put("latitude", 42.1408845);
//        coordinates.put("longitude", -72.5033907);
//        coordinates.put("accuracy", 100);

        coordinates.put("latitude", 10.8231);
        coordinates.put("longitude", 106.6297);
        coordinates.put("accuracy", 100);
        /* Command to emulate Geolocation */
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.navigate().to(URL);
        driver.manage().window().maximize();

        /* driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); */
        /* Not a good programming practice, added for demonstration */
        Thread.sleep(5000);

        WebElement location_icon = driver.findElement(By.cssSelector(".icon-geolocate"));
        Thread.sleep(2000);
        location_icon.click();

        Thread.sleep(6000);
        System.out.println("Geolocation testing with Selenium is complete");
    }

    @Test
    public void test_Selenium4_Geolocation1() throws InterruptedException {
        Map< String,Object> coordinates = new HashMap< String,Object>();
        Map< String,Object> permission = new HashMap< String,Object>();


        String[] permissionArray = { "geolocation"};
        permission.put("permissions", permissionArray);
        driver.executeCdpCommand("Browser.grantPermissions", permission);


        /* Create a hashmap for latitude, longitude, and accuracy as needed by Google Maps */
        coordinates.put("latitude", 42.1408845);
        coordinates.put("longitude", -72.5033907);
        coordinates.put("accuracy", 100);

//        coordinates.put("latitude", 10.8231);
//        coordinates.put("longitude", 106.6297);
//        coordinates.put("accuracy", 100);
        /* Command to emulate Geolocation */
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.navigate().to("https://www.openstreetmap.org/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.navigate().to("https://www.google.com/");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
