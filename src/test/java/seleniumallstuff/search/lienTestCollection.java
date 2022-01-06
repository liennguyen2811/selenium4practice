package seleniumallstuff.search;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;

import java.util.Optional;


public class lienTestCollection {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();

        DevTools devTools = ((ChromeDriver) webDriver).getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(),request -> {

            if(request.getRequest().getUrl().contains("shetty")){
                String mockUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(mockUrl);
                devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(mockUrl),Optional.of(request.getRequest().getMethod()),Optional.empty(), Optional.empty()));
            }
            else {

                devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(request.getRequest().getUrl()),Optional.of(request.getRequest().getMethod()),Optional.empty(), Optional.empty()));
            }
        });

        webDriver.get("https://rahulshettyacademy.com/angularAppdemo/");
        webDriver.findElement(By.xpath("button[type='button'][routerlink='/library']")).click();

    }
}


