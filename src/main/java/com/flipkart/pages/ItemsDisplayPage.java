package com.flipkart.pages;

import org.openqa.selenium.support.PageFactory;

import com.flipkart.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ItemsDisplayPage extends ParentDriver 
{
	public static String Price_BeforeAddingToCart;

	public ItemsDisplayPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.flipkart.android:id/not_now_button")
	private AndroidElement NotNow_Button_InLocationPopup;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"ADD TO CART\"]")
	AndroidElement AddToCart_Button;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"GO TO CART\"]")
	AndroidElement GoToCart_Button;

	public String getItemPrice(String ItemName) 
	{
		NotNow_Button_InLocationPopup.click();
		if (driver.findElementsByXPath("//android.view.ViewGroup/preceding-sibling::android.widget.TextView[contains(@text,\""+ ItemName + "\")]").size() > 0) 
		{
			String Price_BeforeAddingToCart;
			Price_BeforeAddingToCart = driver.findElementByXPath("//android.widget.TextView[contains(@text,\"" + ItemName + "\")]/following-sibling::android.widget.TextView[contains(@text,\"₹\")]").getText();
			driver.findElementByXPath("//android.widget.TextView[contains(@text,\"" + ItemName+ "\")]/following-sibling::android.widget.TextView[contains(@text,\"₹\")]").click();
		
			 ItemName_BeforeAddingToCart= driver.findElementByXPath("//android.widget.TextView[contains(@text,\"" + ItemName+ "\")]").getText();
		} 			
	return Price_BeforeAddingToCart;
	}
	public String getItemName_BeforeAddingToCart() 
	{
		return ItemName_BeforeAddingToCart;
	}
	
	public CartViewPage GoToCart() 
	{
		AddToCart_Button.click();
		GoToCart_Button.click();
		return new CartViewPage(driver);
	}

}
