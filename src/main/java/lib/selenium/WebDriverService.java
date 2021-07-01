package lib.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import lib.dataEnum.Locators;
import lib.dataEnum.eKeys;

public interface WebDriverService {

		
	/**
	 * This method will locate the element using any given locator
	 * @param locator  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 * @author Shivaaram
	 * @throws NoSuchElementException
	 */
	public WebElement locateElement(Locators locator, String locValue) ;	

	/**
	 * This method will locate the elements using any given locator
	 * @param locator  - The locator by which the elements to be found
	 * @param locValue - The locator value by which the element to be found
	 * @author Shivaaram
	 * @throws NoSuchElementException
	 */
	public List<WebElement> locateElements(Locators locator, String locValue) ;	
	
	/**
	 * This method will locate the elements until its visible
	 * @param locator  - The locator by which the elements to be found
	 * @param locValue - The locator value by which the element to be found
	 * @author Shivaaram
	 * @throws NoSuchElementException
	 */
	
	public  WebElement isElementVisible(WebElement ele);


	/**
	 * This method will enter the value in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement
	 * @author Shivaaram
	 * @throws ElementNotVisibleException
	 */
	
	public void type(WebElement ele, String data) ;

	
	/**
	 * This method will enter the value and key in the given text field 
	 * @param ele   - The Webelement (text field) in which the data to be entered
	 * @param data  - The data to be sent to the webelement
	 * @author Shivaaram
	 * @throws ElementNotVisibleException		 * 
	 */

	public void type(WebElement ele, String data,eKeys keyvalue) ;
	
	/**
	 * This method will enter key in the given text field or element
	 * @param ele   - The Webelement (text field) in which the key to be entered
	 * @param data  - The key to be sent to the webelement
	 * @author Shivaaram
	 * @throws ElementNotVisibleException		 * 
	 */	
	public void typeOnlyKeys(WebElement ele, eKeys keyvalue);


	/**
	 * 
	 * This method will click the element and take snap
	 * @param ele   - The Webelement (button/link/element) to be clicked
	 * @author Shivaaram
	 */
	public void click(WebElement ele);
	
	/**
	 * This method will click using JavascriptExecutor
	 * @param ele   - The Webelement (button/link/element) to be clicked
	 * @author Shivaaram
	 */
	public void clickUsingJS(WebElement ele);
	
	/**
	 * This method will click using Actions Class
	 * @param ele   - The Webelement (button/link/element) to be clicked
	 * @author Shivaaram
	 */
	public void clickUsingActions(WebElement ele);

	/**
	 * This method will get the text of the element
	 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
	 * @author Shivaaram
	 */
	public String getText(WebElement ele);

	/**
	 * This method will get the attribute of the element
	 * @param ele   - The Webelement (button/link/element) in which attribute to be retrieved
	 * @author Shivaaram
	 */
	
	public String getAttribute(WebElement ele, String attribute);

	/**
	 * This method will select the drop down visible text
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Shivaaram
	 */
	
	public void selectDropDownUsingVisibleText(WebElement ele, String value) ;

	/**
	 * This method will select the drop down using value
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param value The value attribute to be selected from the dropdown 
	 * @author Shivaaram
	 */
	public void selectDropDownUsingValue(WebElement ele, String value) ;
	
	/**
	 * This method will select the drop down using index
	 * @param ele   - The Webelement (dropdown) to be selected
	 * @param index The index to be selected from the dropdown 
	 * @author Shivaaram
	 */
	public void selectDropDownUsingIndex(WebElement ele, int index) ;

	/**
	 * This method will get the browser title
	 * @param title - The browser title will be fetched
	 * @author Shivaaram
	 */
	
	public String getTitle();
	
	/**
	 * This method will verify browser actual title with expected
	 * @param title - The expected title of the browser
	 * @author Shivaaram
	 */
	public boolean verifyExactTitle(String expectedTitle);
	
	/**
	 * This method will verify browser actual title with expected text using contains
	 * @param title - The expected title of the browser
	 * @author Shivaaram
	 */
	public boolean verifyPartialTitle(String expectedTitle);


	/**
	 * This method will verify exact given text with actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author Shivaaram
	 */
	public void verifyExactText(WebElement ele, String expectedText);

	/**
	 * This method will verify given text contains actual text on the given element
	 * @param ele   - The Webelement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @author Shivaaram
	 */
	public void verifyPartialText(WebElement ele, String expectedText);

	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param ele   - The Webelement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author Shivaaram
	 */
	public void verifyExactAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify partial given attribute's value with actual value on the given element
	 * @param ele   - The Webelement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @author Shivaaram
	 */
	public void verifyPartialAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify if the element (Radio button, Checkbox)  is selected
	 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
	 * @author Shivaaram
	 */
	public void verifySelected(WebElement ele);

	/**
	 * This method will verify if the element is visible in the DOM
	 * @param ele   - The Webelement to be checked
	 * @author Shivaaram
	 */
	public void verifyDisplayed(WebElement ele);

	/**
	 * This method will switch to the Window of interest
	 * @param index The window index to be switched to. 0 -> first window 
	 * @author Shivaaram
	 */
	public void switchToWindow(int index);

	/**
	 * This method will switch to the specific frame
	 * @param ele   - The Webelement (frame) to be switched
	 * @author Shivaaram
	 */
	public void switchToFrame(WebElement ele);

	/**
	 * This method will accept the alert opened
	 * @author Shivaaram
	 */
	public void acceptAlert();

	/**
	 * This method will dismiss the alert opened
	 * @author Shivaaram
	 */
	public void dismissAlert();

	/**
	 * This method will return the text of the alert
	 * @author Shivaaram
	 */
	public String getAlertText();

	/**
	 * This method will close the active browser
	 * @author Shivaaram
	 */
	public void closeActiveBrowser();		

	/**
	 * This method will close all the browsers
	 * @author Shivaaram
	 */
	public void closeAllBrowsers();
	
	
	/**
	 * This method will slow down the Java execution.
	 * @author Shivaaram
	 */
	public void sleep(long seconds);
	
	/**
	 * This method will scroll to element using JavascriptExecutor
	 * @author Shivaaram
	 */
	public void scrollToElementUsingJS(Locators locator, String locValue);
	
	/**
	 * This method will return the size of the Weblements
	 * @author Shivaaram
	 */

	public int sizeOfWebElements(List<WebElement> ele) ;	
	
	/**
	 * This method will wait until expected condition is satisfied
	 * @author Shivaaram
	 */

	public void webDriverWait(String condition, Locators locator, String locatorValue);
	
	/**
	 * This method will return By value
	 * @author Shivaaram
	 */
	public By returnByValue(Locators locator, String locValue);
	
	/**
	 * This method will compare the size of list and return output based on condition provided
	 * @author Shivaaram
	 */
	public void compareWebElementsSize(String condition, int list1Size, int list2Size);
	
	/**
	 * This method will scroll down inside the table
	 * @author Shivaaram
	 */
	public void scrollDown();
	
	/**
	 * This method will reload the webpage
	 * @author Shivaaram
	 */
	public void pageReload();
		
	/**
	 * This method will switch to the New Window loaded with the New url
	 * @param index The window index to be switched to. 0 -> first window 
	 * @author Shivaaram
	 */	
	public void switchToNewWindow(String url);

	
}


