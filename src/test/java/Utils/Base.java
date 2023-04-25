package Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    protected static WebDriver driver;

    private Base() {
    }

    public static WebDriver getDriver() throws IOException {
        if (driver == null) {
            return InitialiseDriver();
        } else {
            return driver;
        }
    }

    private static WebDriver InitialiseDriver() throws IOException {

        String browserName = System.getProperty("Browser");

        System.out.println("Browser name got from jenkins is "+browserName);

        if (browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }

        //        System.setProperty("webdriver.chrome.driver", "C:\\Resources\\chromedriver.exe");
        driver.get(ReadProperties.getPropertyValue("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}