package lib.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.dataEnum.Locators;
import lib.dataEnum.eKeys;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverServiceImpl implements WebDriverService{

	long timeOuts = 30;
	long maxWaitTime =10;
	public Actions act;
	public static Properties prop = new Properties();

	RemoteWebDriver driver = null;
	WebDriverWait wait = null;

	public WebElement locateElement(Locators locator, String locValue) {

		switch (locator) {
		case className: return driver.findElement(By.className(locValue));
		case id: return driver.findElement(By.id(locValue));
		case link: return driver.findElement(By.linkText(locValue));
		case name:return driver.findElement(By.name(locValue));
		case partialLinkText: return driver.findElement(By.partialLinkText(locValue));
		case tagName: return driver.findElement(By.tagName(locValue));
		case xpath:	return driver.findElement(By.xpath(locValue));
		default:
			break;
		}
		return null;
	}


	public List<WebElement> locateElements(Locators locator, String locValue) {

		switch (locator) {
		case className: return driver.findElements(By.className(locValue));
		case id: return driver.findElements(By.id(locValue));
		case link: return driver.findElements(By.linkText(locValue));
		case name:return driver.findElements(By.name(locValue));
		case partialLinkText: return driver.findElements(By.partialLinkText(locValue));
		case tagName: return driver.findElements(By.tagName(locValue));
		case xpath:	return driver.findElements(By.xpath(locValue));
		default:
			break;
		}
		return null;
	}
	public  WebElement isElementVisible(WebElement ele) {
		WebElement element = wait.withMessage("Element is not visible").until(ExpectedConditions.visibilityOf(ele));
		return element;
	}
	
	public void type(WebElement ele, String data) {
			
		try {
			WebElement element = isElementVisible(ele);
			element.clear();
			element.sendKeys(data);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
		} catch (InvalidElementStateException e) {
			System.out.println("The data: "+data+" could not be entered in the field :"+ele);
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while entering "+data+" in the field :"+ele);
		}
	}

	public void type(WebElement ele, String data,eKeys keyvalue) {
		try {
			WebElement element = isElementVisible(ele);
			element.clear();
			switch (keyvalue) {
			case tab: ele.sendKeys(data,Keys.TAB);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
            case Shift: ele.sendKeys(data,Keys.SHIFT);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
            case enter: ele.sendKeys(data,Keys.ENTER);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
            case home:ele.sendKeys(data,Keys.HOME);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
            case space:	ele.sendKeys(data,Keys.SPACE);
			System.out.println("The data: "+data+" entered successfully in the field :"+ele);
			default:
				break;

			}
			}
	 catch (InvalidElementStateException e) {
			System.out.println("The data: "+data+" "+keyvalue+" could not be entered in the field :"+ele);
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while entering "+data+" "+keyvalue+" in the field :"+ele);
		}
	}

	public void click(WebElement ele) {
		WebElement element = wait.withMessage("Element is not clickable").until(ExpectedConditions.elementToBeClickable(ele));
		element.click();

	}

	public void clickUsingJS(WebElement ele) {
		WebElement element = wait.withMessage("Element is not clickable").until(ExpectedConditions.elementToBeClickable(ele));
		driver.executeScript("arguments[0].click()", element);
	}
	
	public void clickUsingActions(WebElement ele) {
		WebElement element = wait.withMessage("Element is not clickable").until(ExpectedConditions.elementToBeClickable(ele));
        act = new Actions(driver);
        act.moveToElement(ele).click().perform();
       
    }

	public String getText(WebElement ele) {
		String bReturn = "";
		bReturn = ele.getText();
		return bReturn;
	}

	public String getAttribute(WebElement ele, String attribute) {		
		String bReturn = "";
		try {
			bReturn=  ele.getAttribute(attribute);
		} catch (WebDriverException e) {
			System.out.println("The element: "+ele+" could not be found.");
		} 
		return bReturn;
	}

	public void selectDropDownUsingVisibleText(WebElement ele, String value) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByVisibleText(value);

	}

	public void selectDropDownUsingValue(WebElement ele, String value) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByValue(value);

	}


	public void selectDropDownUsingIndex(WebElement ele, int index) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByIndex(index);

	}
	public String getTitle() {		
		String bReturn = "";
		try {
			bReturn =  driver.getTitle();
		} catch (WebDriverException e) {
			System.out.println("Unknown Exception Occured While fetching Title");
		} 
		return bReturn;
	}


	public boolean verifyExactTitle(String expectedTitle) {
		boolean bReturn =false;
		try {
			if(getTitle().equals(expectedTitle)) {
				System.out.println("The title of the page matches with the value :"+expectedTitle);
				bReturn= true;
			}else {
				System.out.println("The title of the page:"+driver.getTitle()+" did not match with the value :"+expectedTitle);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the title");
		} 
		return bReturn;
	}

	
	public boolean verifyPartialTitle(String expectedTitle) {
		boolean bReturn =false;
		try {
			if(getTitle().contains(expectedTitle)) {
				System.out.println("The title of the page matches with the value :"+expectedTitle);
				bReturn= true;
			}else {
				System.out.println("The title of the page:"+driver.getTitle()+" did not match with the value :"+expectedTitle);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the title");
		} 
		return bReturn;		
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).equals(expectedText)) {
				System.out.println("The text: "+getText(ele)+" matches with the value :"+expectedText);
			}else {
				System.out.println("The text "+getText(ele)+" doesn't matches the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 

	}
	public void verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).contains(expectedText)) {
				System.out.println("The expected text contains the actual "+expectedText);
			}else {
				System.out.println("The expected text doesn't contain the actual "+expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 
	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).equals(value)) {
				System.out.println("The expected attribute :"+attribute+" value matches the actual "+value);
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not matches the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		} 

	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).contains(value)) {
				System.out.println("The expected attribute :"+attribute+" value contains the actual "+value);
			}else {
				System.out.println("The expected attribute :"+attribute+" value does not contains the actual "+value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
	}		

	public void verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				System.out.println("The element "+ele+" is selected");
			} else {
				System.out.println("The element "+ele+" is not selected");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		}
	}		

	public void verifyDisplayed(WebElement ele) {

		try {
			if(ele.isDisplayed()) {
				System.out.println("The element "+ele+" is visible");
			} else {
				System.out.println("The element "+ele+" is not visible");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : "+e.getMessage());
		} 
	}

	public void switchToWindow(int index) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		List<String> allHandles = new ArrayList<>();
		allHandles.addAll(allWindowHandles);
		driver.switchTo().window(allHandles.get(index));

	}

	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);

	}

	public void acceptAlert() {
		String text = "";
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
		alert.accept();

	}

	public void dismissAlert() {
		String text = "";
		Alert alert = driver.switchTo().alert();
		text = alert.getText();
		alert.dismiss();

	}

	public String getAlertText() {
		String text = "";
		Alert alert = driver.switchTo().alert();
		text = alert.getText();

		return text;
	}

	public void closeActiveBrowser() {
		driver.close();

	}

	public void closeAllBrowsers() {
		driver.quit();

	}

	public void sleep(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
		} catch (Exception e) {
		}
	}

	public void scrollToElementUsingJS(Locators locator, String locValue) {
		String text="";
		WebElement ele = locateElement(locator,locValue);
		text=ele.getText();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);

	}

	public int sizeOfWebElements(List<WebElement> elements) {
		int size=0;
		try {
			size = elements.size();
			System.out.println("Size of the list is: "+size);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Size throws indexoutofbound exception");
		} catch (Exception e) {
			System.out.println("Unknown exception occured waiting");
		}			return size;
	}

	public void webDriverWait(String condition, Locators locator, String locatorValue) {
		WebElement element=null;
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);	
			condition = condition.toLowerCase();

			switch(condition) {
			case "stalenessRefreshed": {
				element = locateElement(locator, locatorValue);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
				break;
			}
			case "clickable": {
				element = locateElement(locator, locatorValue);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				break;
			}
			case "visibility": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(returnByValue(locator,locatorValue)));
				break;
			}
			case "invisibility": {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(returnByValue(locator,locatorValue)));
				break;
			}
			case "presence": {
				wait.until(ExpectedConditions.presenceOfElementLocated(returnByValue(locator,locatorValue)));
				break;
			}
			default: break;
			}
		}catch (NoSuchElementException e) {
			System.out.println("The elements "+element.getText()+" not found.");
		}catch (ElementClickInterceptedException e) {
			System.out.println("Clicking on the element: "+element.getText()+" is intercepted");
		}catch (ElementNotVisibleException e) {
			System.out.println("The element: "+element.getText()+" is not visible");
		}catch (WebDriverException e) {
			System.out.println("Unknown exception occured while finding "+element.getText());
		}

	}

	public By returnByValue(Locators locator, String locValue) {
			try {
				switch (locator) {
				case id: return By.id(locValue);

				case name: return By.name(locValue);

				case className: return By.className(locValue);

				case link : return By.linkText(locValue);

				case xpath: return By.xpath(locValue);	
				
				case partialLinkText: return By.partialLinkText(locValue);
				
				case tagName: return By.tagName(locValue);
				default:
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println("The element with locator "+locator+" not found.");
			} catch (WebDriverException e) {
				System.out.println("Unknown exception occured while finding "+locator+" with the value "+locValue);
			}
			return null;
		}


	public void compareWebElementsSize(String condition, int list1Size, int list2Size) {
		try {
			switch(condition) {
			case "greater": {
				if(list1Size>list2Size) {
					System.out.println("List1: "+list1Size+" is greater than list2: "+list2Size);
				}else {
					System.out.println("List1: "+list1Size+" is lesser than list2"+list2Size);
				}
			}
			case "lesser": {
				if(list1Size<list2Size) {
					System.out.println("List2: "+list2Size+" is greater than list1: "+list1Size);
				}else {
					System.out.println("List2: "+list2Size+" is lesser than list1"+list1Size);
				}
			}
			case "equals" : {
				if(list1Size==list2Size) {
					System.out.println("List2: "+list2Size+" is equal to list1: "+list1Size);
				}else {
					System.out.println("List2: "+list2Size+" is not equal to list1"+list1Size);
				}
			}
			default: break;
			}
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Size throws indexoutofbound exception");
		} catch (Exception e) {
			System.out.println("Unknown exception occured waiting");
		} 
	}


	public void scrollDown() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,1000)");
		} catch (NoSuchElementException e) {
			System.out.println("Scroll down the page is not working");
		}catch (WebDriverException e) {
			System.out.println("Scroll down the page is not working");
		}
	}

	public void pageReload() {
		try {
			driver.navigate().refresh();
		}catch (Exception e) {
			System.out.println("Unknown exception occured while reload");
		}

	}
		
	public void switchToNewWindow(String url) {
		try {
			driver.switchTo().newWindow(WindowType.TAB).get(url);
		} catch (NoSuchWindowException e) {
			System.out.println("The driver could not move to the given window by index ");
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}
	}



}
