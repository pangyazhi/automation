package automation.client.actions;

import automation.client.Config;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class BrowserInstance {
    private static BrowserInstance _instance = new BrowserInstance();
    private WebDriver driver;
    Logger logger = LoggerFactory.getLogger("Browser");
    private BrowserInstance(){

    }
    public static BrowserInstance getInstance(){
        return _instance;
    }

    public WebDriver get(){
        if(driver == null)
            start();
        return driver;
    }

    public void start(){
        if(Config.getInstance().get("browser.host","local").toString().equalsIgnoreCase("sauce")){
            startSauceBrowser();

        }else{
            startLocalBrowser();
        }
    }

    public void close(){
        if(driver==null) return;
        driver.quit();
    }



    private void startSauceBrowser() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(Config.getInstance().get("sauce.browser", "firefox").toString());
        capabilities.setCapability("version", Config.getInstance().get("sauce.browser.version", "17"));

        capabilities.setCapability("platform", Platform.valueOf(Config.getInstance().get("sauce.os", "WINDOWS").toString()));
        capabilities.setCapability("name", Config.getInstance().get("sauce.test.name", "AutoX"));
        capabilities.setCapability("record-screenshots",true);
        capabilities.setCapability("public",Config.getInstance().get("sauce.public","true").toString().equalsIgnoreCase("true"));
        try {
            driver = new RemoteWebDriver(
                    new URL("http://" + Config.getInstance().get("sauce.user", "No User").toString()
                            + ":" + Config.getInstance().get("sauce.key", "No key").toString()
                            + "@ondemand.saucelabs.com:80/wd/hub"),
                    capabilities);

        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }
        String url = Config.getInstance().get("test.url", "about:blank").toString();

        driver.get(url);
    }

    private void startLocalBrowser(){
        String browserName = Config.getInstance().get("local.browser", "firefox").toString();
        DesiredCapabilities capabilities = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities = chromeCapabilities;
            driver = new ChromeDriver(options);

        }

        if (browserName.equalsIgnoreCase("firefox")) {
            capabilities = DesiredCapabilities.firefox();
            driver = new FirefoxDriver(capabilities);
        }

        if (browserName.equalsIgnoreCase("iexplorer")) {
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver = new InternetExplorerDriver(capabilities);
        }

        if (capabilities == null)
            throw new RuntimeException(String.format("Do not support this local browser: %s", browserName));
        String url = Config.getInstance().get("test.url", "about:blank").toString();
        driver.get(url);
    }
}
