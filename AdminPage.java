package com.ibm.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.PropertiesFileHandler;

public class AdminPage {
	
	By logOutLoc = By.xpath("//a[@title='Logout']");
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(linkText="Logout")
	WebElement logOutEle;
	
	public AdminPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
	}
	
	public void launchAdminPage() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
						
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
	}
	
	public String getCurrentTitle() {
		wait.until(ExpectedConditions.presenceOfElementLocated(logOutLoc));
		String actualTitle = driver.getTitle();
		return actualTitle;
	}
	
	public void clickOnLogOut()
	{
		logOutEle.click();
	}
	
}
