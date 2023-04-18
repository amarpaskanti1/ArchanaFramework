package automation;

import PageObjects.FlightReservationPage;
import PageObjects.HotelsPage;
import Utils.Base;
import Utils.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hotels {

    public WebDriver driver;
    public CommonFunctions commonFunctions;
    public HotelsPage hotelsPage;
    private static Logger log = LogManager.getLogger(Hotels.class.getName());

    public Hotels() throws IOException {
        driver = Base.getDriver();
        commonFunctions = new CommonFunctions();
        hotelsPage=new HotelsPage(driver);
    }

    @Test(priority=1)
    public void validateHotelsFunctionality() throws InterruptedException {

        commonFunctions.customClick(hotelsPage.getHotelsTab());
        commonFunctions.enterText(hotelsPage.getDestinationCityInput(),"Pune");
        commonFunctions.selectCustomDropDownValue(hotelsPage.getAdultDropDown(),"2");
        commonFunctions.selectCustomDropDownValue(hotelsPage.getChildDropDown(),"1");
        commonFunctions.selectCustomDropDownValue(hotelsPage.getInfantDropDown(),"3");

    }




}
