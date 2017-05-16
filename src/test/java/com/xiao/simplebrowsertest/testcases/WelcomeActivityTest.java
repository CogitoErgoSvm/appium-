package com.xiao.simplebrowsertest.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xiao.simplebrowsertest.elements.LoginActivityElements;
import com.xiao.simplebrowsertest.elements.WelcomeActivityElements;
import com.xiao.simplebrowsertest.readdata.GetExcelData;

import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;

/**
 * 
 * @author yx
 * @description 首页测试
 *
 */

public class WelcomeActivityTest extends BaseCasePrepare {

	// 延时，单位： ms
	// int elementTimeOut = 300;

	
	@DataProvider(name = "WelcomeData")
	public Object[][] WelcomeData() throws BiffException, IOException {
		GetExcelData getExcelData = new GetExcelData(path, "welcome");
		return getExcelData.getExcelData();
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Welcome页面点击"立即体验"进入登录页
	 * 
	 * @throws InterruptedException
	 */
	@Test(dataProvider="WelcomeData")
	public void clickStart(String expected) throws InterruptedException {
		System.out.println("****首页测试开始****");
		boolean result ;
		
		// 检查首页元素
		appiumUtils.waitForElementToLoad(driver, elementTimeOut, WelcomeActivityElements.startText);
		result = appiumUtils.isTextCorrect(appiumUtils.getText(driver, WelcomeActivityElements.startText), expected);
		
		
		if(result == true){
			// 点击进入登录页
			appiumUtils.click(driver, WelcomeActivityElements.startText);
			Thread.sleep(elementTimeOut);
			checkAfterClick();
			((AndroidDriver) driver).pressKeyCode(BACK_CODE);
		}
		
		System.out.println("****首页测试完成****");
	}

	/**
	 * 检查登录页面元素
	 */
	@SuppressWarnings("unchecked")
	public void checkAfterClick() {
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.usernameLabel);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.passwordLabel);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.edit_username);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.edit_password);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.login_btn);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, LoginActivityElements.regist_btn);
	}
}
