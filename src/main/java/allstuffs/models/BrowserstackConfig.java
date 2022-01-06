package allstuffs.models;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackConfig {
    private static final String BS_USER = "liennguyen3";
    private static final String BS_ACCESS_KEY = "TUhRyuzzDsD6Dw7r1Gc4";
    public String user, accessKey, restUri;
    public URL hub;

    public BrowserstackConfig() {
        this.user = BS_USER;
        this.accessKey = BS_ACCESS_KEY;
        String bsHub = String.format("http://%s:%s@hub-cloud.browserstack.com/wd/hub", user, accessKey);
        try {
            this.hub = new URL(bsHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.restUri = String.format("https://%s:%s@www.browserstack.com/automate", user, accessKey);
    }
}
