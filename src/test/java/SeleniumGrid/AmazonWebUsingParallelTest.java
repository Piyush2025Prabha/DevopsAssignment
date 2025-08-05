package SeleniumGrid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class AmazonWebUsingParallelTest {
	  public WebDriver Driver;	  
	  @Parameters({"bname"})
	  @Test
	  public void crossBrowserTest(String bname) throws MalformedURLException, InterruptedException {
			System.out.println("The remote driver connectivity is about to start");
		  if(bname.equals("Chrome")) {
			  ChromeOptions Options = new ChromeOptions();
			  Driver = new RemoteWebDriver(new URL("http://localhost:4444"),Options);
			  System.out.println("Session created on Chrome");
		  }else if(bname.equals("Firefox")) {
			  FirefoxOptions Options = new FirefoxOptions();
			  Driver = new RemoteWebDriver(new URL("http://localhost:4444"),Options);
			  System.out.println("Session created on Firefox");
		  }else if(bname.equals("Edge")) {
			  EdgeOptions options = new EdgeOptions();
			  Driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options); 		  
			  System.out.println("Session created on Edge");
		  }
		  System.out.println("Remote Driver connectivity is completed successfully");
		  Driver.manage().window().maximize();
		  Driver.navigate().to("https://www.amazon.in/");
		  CaptureScreenshot();
		  String PageTitle = Driver.getTitle();
		  System.out.println("The title of the page is: " + PageTitle);
  }
	  public void CaptureScreenshot() {
		  String ScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" +System.currentTimeMillis()+".png";
		  File SourceFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
		  File DestFile = new File (ScreenshotPath);
		  try {
			FileHandler.copy(SourceFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  @AfterTest
	  public void closeBrowser() {
		  Driver.quit();
	  }
  }
