package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelsPage {
    public HotelsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='Hotels']")
    private WebElement hotelsTab;

    public WebElement getHotelsTab() {
        return hotelsTab;
    }

    @FindBy(id = "ctl00_mainContent_txtOriginStation1_MST")
    private WebElement destinationCityInput;

    public WebElement getDestinationCityInput() {
        return destinationCityInput;
    }

    @FindBy(id = "ddl_Adult_MST")
    private WebElement adultDropDown;

    public WebElement getAdultDropDown() {
        return adultDropDown;
    }

    @FindBy(id = "ddl_Child_MST")
    private WebElement childDropDown;

    public WebElement getChildDropDown() {
        return childDropDown;
    }

    @FindBy(id = "ddl_Infant_MST")
    private WebElement infantDropDown;

    public WebElement getInfantDropDown() {
        return infantDropDown;
    }
}
