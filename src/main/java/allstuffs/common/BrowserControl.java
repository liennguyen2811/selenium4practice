package allstuffs.common;

import allstuffs.models.BrowserstackConfig;
import allstuffs.models.GridConfig;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public final class BrowserControl {
    public static WebDriver browser;
    public TestConfig.Driver hub;
    public GridConfig grid;
    public BrowserstackConfig browserStack;

    public BrowserControl() {
        hub = TestConfig.getHub();
        grid = new GridConfig();
        browserStack = new BrowserstackConfig();
    }

    public static WebDriver getDriver() {
        return browser;
    }

    public WebDriver newBrowser() {
        switch (hub) {
            case GRID:
                return browser = setGridBrowser(TestConfig.getBrowser());
            case BROWSERSTACK:
                return browser = setBrowserstackBrowser(TestConfig.getTestNameForBrowserStack());
            default:
           return browser = setLocalBrowser(TestConfig.getBrowser());
        }
    }

    private WebDriver setLocalBrowser(TestConfig.Browser browser) {
        setDriversPath();
        switch (browser) {
            case FIREFOX:
                return new FirefoxDriver();
            case IE:
                return new InternetExplorerDriver();
            case SAFARI:
                return new SafariDriver();
            case EDGE:
                return new EdgeDriver();
            default:
                return new ChromeDriver();
        }
    }
    private void setDriversPath() {
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/windows/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "./src/bin/windows/geckodriver.exe");
//            todo: go to https://selenium.dev/documentation/en/webdriver/driver_requirements/#quick-reference
//             and download the binaries(ie, edge, edge chromium) and place them under src/bin/windows if you need them
            System.setProperty("webdriver.ie.driver", "./src/bin/windows/IEDriverServer.exe");
            System.setProperty("webdriver.edge.driver", "./src/bin/windows/msedgedriver.exe");
        } else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty("webdriver.chrome.driver", "./src/bin/linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/linux/geckodriver");
            }
        else {
            System.setProperty("webdriver.chrome.driver", "./src/bin/macOS/chromedriver");
            System.setProperty("webdriver.gecko.driver", "./src/bin/macOS/geckodriver");
        }
    }

    private WebDriver setBrowserstackBrowser(String testName) {
        return new RemoteWebDriver(browserStack.hub, get_bs_capabilities(testName));
    }

    private WebDriver setGridBrowser(TestConfig.Browser browser) {
        return new RemoteWebDriver(grid.hub, get_grid_capabilities(browser));
    }

    private DesiredCapabilities get_grid_capabilities(TestConfig.Browser browser) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("marionette");
        switch (browser) {
            case CHROME:
                desiredCapabilities.setBrowserName("chrome");
            case FIREFOX:
                desiredCapabilities.setCapability("marionette", false);
                desiredCapabilities.setBrowserName("firefox");
            case IE:
                desiredCapabilities.setBrowserName("internet explore");
            case SAFARI:
                desiredCapabilities.setBrowserName("safari");
            case EDGE:
                desiredCapabilities.setBrowserName("edge");
            default:
                desiredCapabilities.setBrowserName("chrome");
        }
        return desiredCapabilities;
    }

    Capabilities createCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
       // capabilities.setCapability("javascriptEnabled", "true");
        capabilities.setBrowserName("marionette");
        return capabilities;
    }

    private DesiredCapabilities get_bs_capabilities(String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("project", System.getProperty("bsProject"));
        capabilities.setCapability("build", System.getProperty("bsBuild") + " Git branch: " + System.getProperty("bsName"));
        capabilities.setCapability("name", testName);
        capabilities.setCapability("browser", System.getProperty("bsBrowser"));
        capabilities.setCapability("browser_version", System.getProperty("bsBrowserVersion"));
        capabilities.setCapability("os", System.getProperty("bsOs"));
        capabilities.setCapability("os_version", System.getProperty("bsOsVersion"));
        capabilities.setCapability("resolution", System.getProperty("bsResolution"));
        capabilities.setCapability("acceptSslCerts", "true");
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.console", "errors");
        capabilities.setCapability("browserstack.networkLogs", "true");
        return capabilities;
    }

    public static WebDriver reloadBrowser() {
        browser.navigate().refresh();
        return browser;
    }

}
