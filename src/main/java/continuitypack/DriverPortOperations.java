package continuitypack;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;

public class DriverPortOperations
{
    public DriverPortOperations()
    {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
    }
    public ChromeDriver setupChromeDriver()
    {
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }

    public ChromeDriver setupChromeDriver(String port) throws IOException
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:"+ port);
        ChromeDriver driver = new ChromeDriver(options);
        return driver;
    }

    public String getPreviousPortNumber() throws IOException {
        File file = new File("./src/main/resources/portNumber/port.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder outputBuffer = new StringBuilder(fileInputStream.available());
        int line;
        while ((line = fileInputStream.read()) != -1)
            outputBuffer.append((char) line);
        System.out.println("The port number read from file: "+outputBuffer.toString());
        return outputBuffer.toString();
    }

    public void getCurrentPortNumber(ChromeDriver driver) throws IOException {
        Capabilities capabilities = driver.getCapabilities();
        for (String capability : capabilities.getCapabilityNames()) {
            if (capability.equals("goog:chromeOptions")) {
                String debuggerAddress = capabilities.getCapability(capability).toString();
                String portNumber = debuggerAddress.replaceAll("[^0-9]", "");
                System.out.println(portNumber);
                FileOutputStream outputStream = new FileOutputStream("./src/main/resources/portNumber/port.txt");
                byte[] strToBytes = portNumber.getBytes();
                outputStream.write(strToBytes);
                outputStream.close();
                break;
            }
        }
    }
}
