package automation;

import java.io.IOException;

import PageObjects.FlightReservationPage;
import Utils.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import Utils.Base;

public class RahulFlightReservation {
	public WebDriver driver;
	public CommonFunctions commonFunctions;
	public FlightReservationPage flightReservationPage;
	private static Logger log = LogManager.getLogger(RahulFlightReservation.class.getName());

	public RahulFlightReservation() throws IOException {
		driver = Base.getDriver();
		commonFunctions = new CommonFunctions();
		flightReservationPage = new FlightReservationPage(driver);
	}

	@Test(priority=1)
	public void TC01() throws InterruptedException, IOException {

		commonFunctions.selectDropDownValue(By.xpath("//li[@class='ui-menu-item']/a"),"Australia");

		log.info(flightReservationPage.getRoundTripRadio().isSelected());

		commonFunctions.customClick(flightReservationPage.getRoundTripRadio());

		log.debug(flightReservationPage.getRoundTripRadio().isSelected());
		log.error(flightReservationPage.getFamilyAndFriendsCheckBox().isSelected());

		commonFunctions.customClick(flightReservationPage.getFamilyAndFriendsCheckBox());
//		flightReservationPage.getFamilyAndFriendsCheckBox().click();
		log.fatal(flightReservationPage.getFamilyAndFriendsCheckBox().isSelected());

		Assert.assertEquals(driver.getTitle(),"QAClickJet - Flight Booking for Domestic and International, Cheap Air Tickets");
	}

	@Test(priority=2)
	public void validateSearchFunctionality() throws InterruptedException {

		commonFunctions.customClick(flightReservationPage.getSearchButton());

		Thread.sleep(20000);
	}

}
