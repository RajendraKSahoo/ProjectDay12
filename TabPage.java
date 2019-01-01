package com.ibm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.PropertiesFileHandler;


public class TabPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
		public TabPage(WebDriver driver,WebDriverWait wait)
		{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
		}
				
	//WebElement for link Catalog
			@FindBy(xpath="//a[contains(text(),'Catalog')]")
			WebElement catalogEle;
			
	//WebElement for link Tabs
			@FindBy(xpath="//a[contains(text(),'Tabs')]")
			WebElement tabsEle;
			
	//WebElement for icon Add New
			@FindBy(xpath="//a[@title='Add New']")
			WebElement addnewEle;
	
	//WebElement for Search text box
			@FindBy(xpath="//input[@type='search']")
			WebElement searchEle;
			
	//WebElement for button Action
			@FindBy(xpath="(//button[contains(text(), 'Action')])[1]")
			WebElement actionEle;
					
	//WebElement for Edit
			@FindBy(xpath="//a[@title='Edit']")
			WebElement editEle;
		
	//WebElement for field Tab Name
			@FindBy(xpath="//input[@name='name']")
			WebElement tabnameEle;
			
	//WebElement for field Sort
			@FindBy(xpath="//input[@name='sort']")
			WebElement sortEle;
			
	//WebElement for field Status
			@FindBy(xpath="//select[@name='status']")
			WebElement statusddEle;
		
	//WebElement for icon Save
			@FindBy(xpath="//button[@title='Save']")
			WebElement saveEle;
			
	//WebElement for title of panel - Add Tab
			@FindBy(xpath="//div[@class='panel-title']/h4")
			WebElement headerEle;
			
	//WebElement for success message after saving a new record
			@FindBy(xpath="//div[contains(text(),'Success')]")
			WebElement successmsgEle;
			
	//This is a method to click on link Catalog on Admin page
			public void clickOnLinkCatalog()
			{
				catalogEle.click();
			}
	//This is a method to click on link Tabs under Catalog on Admin page		
			public void clickOnLinkTabs()
			{
				tabsEle.click();
			}
	//This is a method to click on icon Add New on Tabs List of Admin page		
			public void clickOnIconAddNew()
			{
				addnewEle.click();
			}
	//This is a method to enter value on field Tab Name using properties file
			public void enterTabName(String tabname)
			{
				tabnameEle.sendKeys(tabname);
			}
	//This is a method to enter value on field Sort Order using properties file		
			public void enterSortOrder(String sortorder)
			{
				sortEle.sendKeys(sortorder);
			}
	//This is a method to select value on field Status		
			public void selectStatus()
			{
				statusddEle.click();
				Select statusEle = new Select(statusddEle);
				statusEle.selectByVisibleText("Enabled");
			}
	//This is a method to perform a Search	
			public void performSearch(String tabname)
			{
				searchEle.sendKeys(tabname);
				searchEle.click();
			}
	//This is a method to select Action icon	
			public void clickOnAction()
			{
				actionEle.click();
			}
	//This is a method to select Edit option			
			public void clickOnEdit()
			{
				editEle.click();
			}
	//This is a method to clear value on field Tab Name		
			public void clearTabName()
			{
				tabnameEle.clear();
			}
	//This is a method to clear value on field Sort Order		
			public void clearSortOrder()
			{
				sortEle.clear();
			}
	//This is a method to clear value on field Status		
			public void clearStatus()
			{
				statusddEle.click();
				Select statusEle = new Select(statusddEle);
				statusEle.selectByVisibleText("");
			}		
	//This is a method to click on icon Save		
			public void clickOnIconSave()
			{
				saveEle.click();
			}
	
	//This is a method to validate the header of Add Tab page		
			public String getHeaderMessage() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedtabheadermsg = data.get("expectedtabheader");
				
				String actualtabheadermsg = headerEle.getText();
				System.out.println("The first validation on presence of Tabs header....");
				System.out.println("The header of Add Tab page: " +actualtabheadermsg);
				Assert.assertEquals(expectedtabheadermsg, actualtabheadermsg);
				return(actualtabheadermsg);
			}
	
	//This is a method to validate the header of Edit Tab page		
			public String getEditHeaderMessage() throws IOException
			{
				String actualtabheadermsg = headerEle.getText();
				System.out.println("The first validation on presence of modified Tabs header....");
				System.out.println("The header of Edit Tab page: " +actualtabheadermsg);
				Assert.assertEquals("Edit Tab", actualtabheadermsg);
				return(actualtabheadermsg);
			}
	
	//This is a method to validate the error message on missing mandatory field - Tab Name
			public String validationOnMisingField() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedmissingtabmessage = data.get("expectedmissingtabmessage");
				
				JavascriptExecutor js=(JavascriptExecutor) driver;
				String actualmissingtabmessage = (String) js.executeScript("return document.getElementsByName('name')[0].validationMessage;");
				System.out.println("The second validation on error message of missing field...");
				System.out.println("The error message on mising tab name: " +actualmissingtabmessage);
				Assert.assertEquals(expectedmissingtabmessage, actualmissingtabmessage);
				return(actualmissingtabmessage);
			}
	//This is a method to validate the error message on missing mandatory field - Sort Order
			public String validationOnMisingSortField() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedmissingtabmessage = data.get("expectedmissingtabmessage");
				
				JavascriptExecutor js=(JavascriptExecutor) driver;
				String actualmissingmessage = (String) js.executeScript("return document.getElementsByName('sort')[0].validationMessage;");
				System.out.println("The second validation on error message of missing field...");
				System.out.println("The error message on mising tab name: " +actualmissingmessage);
				Assert.assertEquals(expectedmissingtabmessage, actualmissingmessage);
				return(actualmissingmessage);
			}
	//This is a method to validate the success message after saving the record
			public String validationOnSuccessMessage() throws IOException
			{
				String file="./TestData/magentodata.properties";
				PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
				HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
				
				String expectedtabmessage = data.get("expectedtabmessage");
				
				String actualtabmessage = successmsgEle.getText();
					if (actualtabmessage.contains(expectedtabmessage))
					{
					System.out.println("The third validation on success message after saving record...");
					System.out.println("The success message on saving the record: " +actualtabmessage);
					Assert.assertTrue(true);
					}
					else
					{
						Assert.fail();
					}
				return(actualtabmessage);
			}
			
	//This is a method to validate the success message after saving the record
			public String validationOnEditSuccessMessage() throws IOException
			{
				String actualsuccessmessage = successmsgEle.getText();
					if (actualsuccessmessage.contains("Success: You have successfully updated tab!"))
					{
					System.out.println("The third validation on success message after saving record...");
					System.out.println("The success message on saving the record: " +actualsuccessmessage);
					Assert.assertTrue(true);
					}
					else
					{
						Assert.fail();
					}
				return(actualsuccessmessage);
			}
			
	//This is a method to validate that record is inserted into the list
			public void validationOnAddRecord() throws FileNotFoundException, IOException
			{
				Properties p = new Properties();
				p.load(new FileInputStream("./TestData/magentodata.properties"));
			
				String pagesource = driver.getPageSource();
								
				if(pagesource.contains(p.getProperty("tabname"))) {
					System.out.println("The validation on presence of record in the Admin panel table is confirmed!");
					Assert.assertTrue(true);
				}
				else {
					System.out.println("The tab name is not added to this list");
					Assert.fail();
				}
			}
	//This is a method to validate that record is inserted into the list
			public void validationOnEditRecord() throws FileNotFoundException, IOException
			{
				Properties p = new Properties();
				p.load(new FileInputStream("./TestData/magentodata.properties"));
			
				String pagesource = driver.getPageSource();
								
				if(pagesource.contains(p.getProperty("edittabname"))) {
					System.out.println("The validation on presence of record in the Admin panel table is confirmed!");
					Assert.assertTrue(true);
				}
				else {
					System.out.println("The tab name is not added to this list");
					Assert.fail();
				}
			}

}
