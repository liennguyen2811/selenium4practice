package seleniumallstuff.devtoolstest;

import driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;

import org.openqa.selenium.devtools.v96.network.model.ConnectionType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;


public class EmulateNetwokSpeedTest extends BaseTestDev {
    private WebDriver localWebDriver;
    private Logger logger = LogManager.getLogger(EmulateNetwokSpeedTest.class);


    @BeforeClass
    public void initialiseClass() {
        threadLocalDriver = LocalDriverManager.getDriver();
        System.out.println("if come here threadLocalDriver--" + threadLocalDriver);
    }

    @Test
    public void simulateNetwork() {
        System.out.println("if come here 1");
        DevTools devTools = ((ChromeDriver) threadLocalDriver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                20,
                20,
                50,
                Optional.of(ConnectionType.CELLULAR2G)
        ));
        threadLocalDriver.get("https://www.zoomcar.com"); //simulate network and then launch
    }
}
