package Utils;

import automation.Hotels;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonFunctions extends Listener {

    public WebDriver driver;
    private static Logger log = LogManager.getLogger(CommonFunctions.class.getName());

    public CommonFunctions() throws IOException {
        driver = Base.getDriver();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void customClick(WebElement ele) {
        ele.click();
        log.debug("Clicked on button " + String.valueOf(ele));
    }

    public void enterText(WebElement ele, String text) throws InterruptedException {
        ele.sendKeys(text);
        log.debug("Entered text "+text +" in " + String.valueOf(ele));
    }

    public void selectCustomDropDownValue(WebElement ele, String value) {
        Select dropDown = new Select(ele);
        dropDown.selectByVisibleText(value);
    }

    public void selectDropDownValue(By ele, String value) throws InterruptedException, IOException {
        enterText(driver.findElement(By.id("autosuggest")), value);
        Thread.sleep(5000);
        List<WebElement> options = driver.findElements(ele);

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(value)) {

                // takeScreenshot(result.getMethod().getMethodName(),driver);
                takeScreenshot("Country Dropdown" + value, driver);
                explicitWait(option);
                jsClick(option);
                break;
            }
        }

    }

    public void selectValueFromCountryDropdown(String CountryName) throws IOException, InterruptedException {
        driver.findElement(By.id("autosuggest")).sendKeys(CountryName);
        Thread.sleep(5000);
        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(CountryName)) {

                // takeScreenshot(result.getMethod().getMethodName(),driver);
                takeScreenshot("Country Dropdown" + CountryName, driver);
                explicitWait(option);
                jsClick(option);
                break;
            }
        }
    }

    public void explicitWait(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }

    public void jsClick(WebElement e) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", e);
    }


    @AfterSuite
    public void tearDownn() {
        driver.quit();
    }
}
