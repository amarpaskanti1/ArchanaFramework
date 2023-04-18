package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class FlightReservationPage {

    public FlightReservationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "ctl00_mainContent_rbtnl_Trip_1")
    private WebElement roundTripRadio;

    @FindBy(id = "ctl00_mainContent_chk_friendsandfamily")
    private WebElement familyAndFriendsCheckBox;

    @FindBy(id = "ctl00_mainContent_btn_FindFlights")
    private WebElement searchButton;

    public WebElement getRoundTripRadio(){
        return roundTripRadio;
    }
    public WebElement getFamilyAndFriendsCheckBox(){
        return familyAndFriendsCheckBox;
    }

    public WebElement getSearchButton(){
        return searchButton;
    }
}
