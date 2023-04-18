package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listener implements ITestListener {

    public WebDriver driver;
    private static Logger log = LogManager.getLogger(Listener.class.getName());
    // builds a new report using the html template
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    // helps to generate the logs in test report.
    public ExtentTest test;

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
        log.info("TC started: " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        // test.log(Status.PASS, "TC passed");
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));

        test.log(Status.INFO, "");
        log.info("TC passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        String path = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());

        } catch (Exception e) {
            System.out.println("Failed to take driver instance");
            e.printStackTrace();
        }

        try {
            log.info("Taking screenshot " + result.getMethod().getMethodName());

            path = takeScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            log.info("Failed to take screenshot " + result.getMethod().getMethodName());
            e.printStackTrace();
        }

        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
        log.info("TC failed: " + result.getMethod().getMethodName() + ", taking screenshot");

        try {
            test.fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(path, "Failed screenshot").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/testReport.html");

        // initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "AP");

        // configuration items to change the look and feel
        // add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public String takeScreenshot(String name, WebDriver driver) throws IOException {
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        // Move image file to new destination

        String path = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + name + getTimeStamp() + name + ".png";
        File DestFile = new File(path);
        // Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

        return path;
    }

    public String getTimeStamp() {
        String dateTime = java.time.LocalDateTime.now().toString();
        dateTime = dateTime.replace("-", "_");
        dateTime = dateTime.replace(":", "_");
        dateTime = dateTime.replace(".", "_");
        return dateTime;
    }

}