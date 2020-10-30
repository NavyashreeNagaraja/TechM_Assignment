package com.flipkart.tests;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import com.flipkart.pages.*;
import com.flipkart.pages.LoginPage;
import com.flipkart.parentdriver.ParentDriver;

import com.flipkart.utilities.ReadExcel;

import io.appium.java_client.android.AndroidDriver;

public class Test1 
{
	WelcomePage WelcomePage_Object;
	LoginPage LoginPage_Object;
	SearchItemPage SearchItemPage_Object;
	ItemsDisplayPage ItemsDisplayPage_Object;
	CartViewPage CartViewPage_Object;
	ParentDriver ParentDriver_Object;
	public static AndroidDriver driver;
	
	public ReadExcel Excel_Object = new ReadExcel();
	String FilePath = System.getProperty("user.dir")+"/src/test/resources/TestData";

	
	@BeforeTest
	public void Setup()throws IOException
	{
		
		Excel_Object.readExcel(FilePath, "InputDataFile.xlsx", "InputSheet1");
		String appPath = "E:\\FlipkartApk\\Flipkart.apk";
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("app", appPath);
		dc.setCapability("newCommandTimeout", 20000);
		dc.setCapability("deviceName", "Android");
		dc.setCapability("platformVersion",  "Android");
		dc.setCapability("platformVersion", "");
		dc.setCapability("deviceReadyTimeout", 20000);
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		WelcomePage_Object = new WelcomePage(driver);
	}
	
	@Test(dataProvider="getInputData")
	public void VerifyItemDataInCartView(String Language_Name, String Item_Name) 
	{
		ParentDriver_Object.Entered_ItemName = Item_Name;
		LoginPage_Object = WelcomePage_Object.LanguageSelection(Language_Name);
		SearchItemPage_Object = LoginPage_Object.SkipLogin();
		ItemsDisplayPage_Object = SearchItemPage_Object.SearchItem(Item_Name);

		String Price_BeforeAddingToCart = ItemsDisplayPage_Object.getItemPrice(Item_Name);
		String ItemName_BeforeAddingToCart= ItemsDisplayPage_Object.getItemName_BeforeAddingToCart();
		
		CartViewPage_Object = ItemsDisplayPage_Object.GoToCart();
		String Price_InCartView = CartViewPage_Object.getPrice_In_CartView();
		
		//Verifying whether the Item price in the Cart View is same as the  Item Price before adding to cart.
		Assert.assertEquals(Price_BeforeAddingToCart.trim(), Price_InCartView.trim());
		
		String ItemName_InCartView= CartViewPage_Object.getItemName_In_CartView();
		
		//Verifying whether the Item Name in the Cart View is same as the Item Name before adding to cart.
		Assert.assertEquals(ItemName_BeforeAddingToCart.trim(), ItemName_InCartView.trim());
	}
	
	@DataProvider
	public Object[][] getInputData() throws Exception 
	{	
		int TotalNumberOfRows = Excel_Object.getTotalNumberOfRows();
		int TotalNumberOfColumns = Excel_Object.getTotalNumberOfColumns();
		Object[][] data = new Object[TotalNumberOfRows-1][TotalNumberOfColumns];
		
		for( int row=2; row<=TotalNumberOfRows; row++) 
		{
			for( int column=0; column<TotalNumberOfColumns; column++) 
			{
				data[row-2][column] = Excel_Object.getCellData(row,column);	
			}
		}
		return data;
	}
	
	@AfterTest
	public void Cleanup() 
	{
		driver.quit();
	}
}
