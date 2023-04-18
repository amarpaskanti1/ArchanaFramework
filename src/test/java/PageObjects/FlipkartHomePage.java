package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipkartHomePage {

    public FlipkartHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement searchBox;

    public WebElement getSearchBox() {
        return searchBox;
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    public WebElement getSubmit() {
        return submit;
    }

    @FindBy(xpath = "//button[@class='_2KpZ6l _2doB4z']")
    private WebElement cancelButton;

    public WebElement getCancelButton() {
        return cancelButton;
    }
}

