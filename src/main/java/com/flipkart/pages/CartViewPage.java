package com.flipkart.pages;

import org.openqa.selenium.support.PageFactory;

import com.flipkart.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartViewPage extends ParentDriver 
{
	public CartViewPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	} 
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"₹\")")
	AndroidElement Price_InCartView;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"+ Entered_ItemName + \")")
	AndroidElement ItemName_InCartView;
	
	public String getPrice_In_CartView() 
	{
		return Price_InCartView.getText();
	}
	public String getItemName_In_CartView() 
	{
		return ItemName_InCartView.getText();
	}
}
