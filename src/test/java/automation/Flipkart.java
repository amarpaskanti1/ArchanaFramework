package automation;

import PageObjects.FlipkartHomePage;
import Utils.Base;
import Utils.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Flipkart {
    public WebDriver driver;
    public CommonFunctions commonFunctions;
    private static Logger log = LogManager.getLogger(Flipkart.class.getName());
    public FlipkartHomePage flipkartHomePage;

    public Flipkart() throws IOException {
        driver = Base.getDriver();
        commonFunctions = new CommonFunctions();
        flipkartHomePage = new FlipkartHomePage(driver);
    }

    @Test
    public void TC01() throws InterruptedException {
        commonFunctions.customClick(flipkartHomePage.getCancelButton());
        commonFunctions.enterText(flipkartHomePage.getSearchBox(), "Mobile");
        commonFunctions.customClick(flipkartHomePage.getSubmit());

        String str = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
        Assert.assertEquals(str,commonFunctions.getPageTitle());
        System.out.println(str);
        System.out.println("\n Added hook");
        driver.quit();
    }

}
