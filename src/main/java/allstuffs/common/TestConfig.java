package allstuffs.common;

import allstuffs.models.EnvironmentData;

import java.util.HashMap;

public final class TestConfig {
    public static String testNameForBrowserstack;
    public static HashMap<String, String> environmentUrls;
    public static HashMap<String, String> languageUrls;
    public static HashMap<String, HashMap<String, String>> existingElementLanguages;
    public static HashMap<String, HashMap<String, String>> existingUsers;
    public static String getEnvironment() {
        String envEnvironment = System.getProperty("testEnvironment");
        return (envEnvironment != null) ? envEnvironment : "test";
}

    public static Driver getHub() {
        String envHub = System.getProperty("hub");
        envHub = (envHub != null) ? envHub : "local";

        switch (envHub) {
            case "browserstack":
                return Driver.BROWSERSTACK;
            case "grid":
                return Driver.GRID;
            case "local":
                return Driver.LOCAL;
            default:
                return Driver.LOCAL;
        }
    }

    public static Browser getBrowser() {
        String envBrowser = System.getProperty("testBrowser");
        envBrowser = (envBrowser != null) ? envBrowser : "chrome";

        switch (envBrowser.toLowerCase()) {
            case "firefox":
                return Browser.FIREFOX;
            case "chrome":
                return Browser.CHROME;
            case "ie":
                return Browser.IE;
            case "edge":
                return Browser.EDGE;
            case "safari":
                return Browser.SAFARI;
            default:
                return Browser.CHROME;
        }
    }

    public enum Driver {BROWSERSTACK, GRID, LOCAL}

    public enum Browser {FIREFOX, CHROME, IE, EDGE, SAFARI}

    public static void setTestNameForBrowserStack(String testNameForBrowserStack){
        testNameForBrowserstack = testNameForBrowserStack;
    }
    public static String getTestNameForBrowserStack() {
        return testNameForBrowserstack;
    }
    public static void setTestNameForTestFailed(String testNameForBrowserStack){
        testNameForBrowserstack = testNameForBrowserStack;
    }

    public static String getTestNameForTestFailed() {
        return testNameForBrowserstack;
    }

    public static void initEnvironment() {
        EnvironmentData environmentData = new EnvironmentData(TestConfig.getEnvironment());
        environmentUrls = environmentData.environmentUrls;
        existingUsers = environmentData.existingUsers;
        languageUrls = environmentData.languageUrls;
        existingElementLanguages = environmentData.existingElementLanguages;

    }
}