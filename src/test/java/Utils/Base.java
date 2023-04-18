package Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        System.setProperty("webdriver.chrome.driver", "C:\\Resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(ReadProperties.getPropertyValue("URL"));
//        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
