package com.flipkart.pages;

import org.openqa.selenium.support.PageFactory;

import com.flipkart.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends ParentDriver 
{
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}	
	@AndroidFindBy(id="com.flipkart.android:id/custom_back_icon")
	private AndroidElement Skip_Button;
	
	public SearchItemPage SkipLogin() {
		Skip_Button.click();
		return new SearchItemPage(driver);
	}
}
