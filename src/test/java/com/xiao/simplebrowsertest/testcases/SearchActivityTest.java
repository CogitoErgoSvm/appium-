package com.xiao.simplebrowsertest.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xiao.simplebrowsertest.elements.LoginActivityElements;
import com.xiao.simplebrowsertest.elements.RegisterActivityElements;
import com.xiao.simplebrowsertest.elements.SearchActivityElements;
import com.xiao.simplebrowsertest.elements.WelcomeActivityElements;
import com.xiao.simplebrowsertest.readdata.GetExcelData;
import com.xiao.simplebrowsertest.utils.AppiumUtils;

import io.appium.java_client.AppiumDriver;
import jxl.read.biff.BiffException;

/**
 * 
 * @author yx
 * @description 根据关键词测试搜索
 *
 */
public class SearchActivityTest extends BaseCasePrepare {


	/**
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	@DataProvider(name = "searchData")
	public Object[][] registData() throws BiffException, IOException {
		GetExcelData getExcelData = new GetExcelData(path, "search");
		return getExcelData.getExcelData();
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "searchData")
	public void searchKeyWord(String keyWord) throws InterruptedException {
		System.out.println("this is SearchActivityTest!");
		System.out.println("开始登录app");
		beforeSearch();
		Thread.sleep(2000);
		checkUiWithSearchPage();
		Thread.sleep(elementTimeOut);
		WebElement netAdress = driver.findElement(SearchActivityElements.edit_netAddress);
		WebElement startSearch = driver.findElement(SearchActivityElements.startSearch);
		netAdress.clear();
		Thread.sleep(elementTimeOut);
		System.out.println("输入检索关键字："+keyWord);
		netAdress.sendKeys(keyWord);
		Thread.sleep(elementTimeOut);
		startSearch.click();
		Thread.sleep(elementTimeOut);
		Thread.sleep(elementTimeOut);
		
		checkResultFitKeyword(appiumUtils, driver, keyWord, SearchActivityElements.WEBVIEW);
		Thread.sleep(elementTimeOut);
		cmdCommand.excuteCmd(CLEARDATA);
		Thread.sleep(2000);
		cmdCommand.excuteCmd(LAUNCH_APP);
		
		
		//搜索结果断言
//		System.out.println("断言搜索结果");
//		appiumUtils.switchWebview(driver, SearchActivityElements.WEBVIEW_NAME);
//		Thread.sleep(elementTimeOut);
//		checkResultFitKeyword(appiumUtils, driver, keyWord, SearchActivityElements.webView);
	}

	/**
	 * 
	 * @throws InterruptedException
	 */
//	@Test
//	public void searchReturnHome() throws InterruptedException{
//		beforeSearch();
//		Thread.sleep(elementTimeOut);
//		String key = "appium";
//		WebElement netAdress = driver.findElement(SearchActivityElements.edit_netAddress);
//		WebElement startSearch = driver.findElement(SearchActivityElements.startSearch);
//		WebElement home = driver.findElement(SearchActivityElements.Home);
//		netAdress.clear();
//		Thread.sleep(elementTimeOut);
//		System.out.println("输入检索关键字："+key);
//		netAdress.sendKeys(key);
//		Thread.sleep(elementTimeOut);
//		startSearch.click();
//		Thread.sleep(elementTimeOut);
//		home.click();
//		Thread.sleep(elementTimeOut);
//		String keyStr = netAdress.getText().toString();
//		Assert.assertEquals("", keyStr, "点击回首页后，输入框清空");
//	}
	
	
	
	/**
	 * 等待加载搜索页面元素
	 */
	@SuppressWarnings("unchecked")
	public void checkUiWithSearchPage() {
		appiumUtils.waitForElementToLoad(driver, waitTimeout, SearchActivityElements.Home);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, SearchActivityElements.edit_netAddress);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, SearchActivityElements.startSearch);
	  //appiumUtils.waitForElementToLoad(driver, waitTimeout, SearchActivityElements.progressBar);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, SearchActivityElements.WEBVIEW);
	}

	/**
	 * 搜索页测试准备工作
	 * @throws InterruptedException 
	 */
	public void beforeSearch() throws InterruptedException {
		WebElement startText = driver.findElement(WelcomeActivityElements.startText);
		startText.click();
		Thread.sleep(elementTimeOut);
		WebElement register_btn = driver.findElement(LoginActivityElements.regist_btn);
		register_btn.click();
		Thread.sleep(elementTimeOut);
		WebElement edit_user = driver.findElement(RegisterActivityElements.edit_username);
		WebElement edit_pwd = driver.findElement(RegisterActivityElements.edit_password);
		System.out.println("输入用户名：test");
		edit_user.sendKeys("test");
		System.out.println("输入密码：123456");
		edit_pwd.sendKeys("123456");
		Thread.sleep(elementTimeOut);
		WebElement register = driver.findElement(RegisterActivityElements.register_btn);
		register.click();
		Thread.sleep(elementTimeOut);
		WebElement login_btn = driver.findElement(LoginActivityElements.login_btn);
		login_btn.click();
		System.out.println("登录app成功");
	}

	/**
	 * 验证搜索出来的结果是否含有关键字
	 * 
	 * @param appiumUtil
	 * @param driver
	 * @param keyword
	 * @param byElement
	 */
	public static void checkResultFitKeyword(AppiumUtils appiumUtil, AppiumDriver<WebElement> driver, String keyword,
			By byElement) {
		appiumUtil.isContains(byElement, keyword);
	}

}
