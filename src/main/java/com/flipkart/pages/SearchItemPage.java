package com.flipkart.pages;

import org.openqa.selenium.support.PageFactory;

import com.flipkart.parentdriver.ParentDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

public class SearchItemPage extends ParentDriver 
{
	public SearchItemPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	} 
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Search on flipkart\"]/android.widget.TextView")
	AndroidElement SearchItem_TextBox;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc=\"Search grocery products in Supermart\"]")
	AndroidElement AutoComplete_TextView;
	
	@AndroidFindBy(className="android.widget.TextView")
	List<AndroidElement> ItemList;
	
	public ItemsDisplayPage SearchItem(String ItemName) {
		//waitForElement(Constants.SEARCH_PRODUCT_ID, "ID");
		SearchItem_TextBox.click();
		AutoComplete_TextView.sendKeys(ItemName.toLowerCase());
		for(int i=0; i<=ItemList.size(); i++)
		{
			String CurrentItemName = ItemList.get(i).getText();
			if(CurrentItemName.contains(ItemName)) {
				ItemList.get(i).click();
				break;
			}
		}
		return new ItemsDisplayPage(driver);
	}
}
