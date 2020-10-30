package com.flipkart.pages;

import org.openqa.selenium.support.PageFactory;

import java.util.List;

import com.flipkart.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomePage extends ParentDriver 
{
	public WelcomePage(AndroidDriver<AndroidElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\\\"com.flipkart.android:id/tv_text\\\"]")
	private List <AndroidElement> LanguageList;
	
	@AndroidFindBy(id="com.flipkart.android:id/select_btn")
	private AndroidElement Continue_Button;
	
	public LoginPage LanguageSelection(String Language) 
	{
		for(int i=0; i<=LanguageList.size(); i++) 
		{
			if(LanguageList.get(i).getAttribute("text").contains(Language)) {
				LanguageList.get(i).click();
				Continue_Button.click();
				break;
			}
		}
		return new LoginPage(driver);
	}
}
