package continuitypack;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class ContinuityMain
{
    public static void main(String[] args) throws IOException {

        DriverPortOperations driverPortOperations = new DriverPortOperations();

        //Normal launch of the Chrome Browser on a random open port
        ChromeDriver driver = driverPortOperations.setupChromeDriver();

        //First Operation done on Opened Chrome Browser
        driver.get("https://selenium.dev");
        System.out.println(driver.getTitle());

        //Get the current port number and save it in file
        driverPortOperations.getCurrentPortNumber(driver);

        //Set the chromedriver with same port number which is saved in the file
        driver = driverPortOperations.setupChromeDriver(driverPortOperations.getPreviousPortNumber());

        //Second Operation done on the same Opened Chrome Browser
        driver.get("https://google.co.in");
        System.out.println(driver.getTitle());
    }
}
