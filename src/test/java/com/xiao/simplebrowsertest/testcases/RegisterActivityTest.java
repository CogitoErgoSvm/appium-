package com.xiao.simplebrowsertest.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xiao.simplebrowsertest.elements.LoginActivityElements;
import com.xiao.simplebrowsertest.elements.RegisterActivityElements;
import com.xiao.simplebrowsertest.elements.WelcomeActivityElements;
import com.xiao.simplebrowsertest.readdata.GetExcelData;

import org.testng.Assert;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;

/**
 * 
 * @author yx
 * @description 注册页面测试
 *
 */

public class RegisterActivityTest extends BaseCasePrepare {


	
	// switch 判断条件
	private static final String expectedRegiterFailed = ".activity.RegisterActivity";
	private static final String expectedRegiterSuccessed = ".activity.LoginActivity";
	

	@DataProvider(name = "registData")
	public Object[][] registData() throws BiffException, IOException {
		GetExcelData getExcelData = new GetExcelData(path, "register");
		return getExcelData.getExcelData();
	}



	/**
	 * 注册测试
	 * 
	 * @param username
	 * @param password
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "rawtypes" })
	@Test(dataProvider = "registData")
	public void registerWithData(String username, String password, String expected) throws InterruptedException {
		System.out.println("****Start  testWithRegister****");
		beforeRegister();
		checkUiWithRegisterPage();
		WebElement edit_user = driver.findElement(RegisterActivityElements.edit_username);
		WebElement edit_pwd = driver.findElement(RegisterActivityElements.edit_password);
		WebElement register_btn = driver.findElement(RegisterActivityElements.register_btn);
		System.out.println("clear操作");
		edit_user.clear();
		edit_pwd.clear();

		System.out.println("输入用户名:" + username);
		edit_user.sendKeys(username);
		Thread.sleep(elementTimeOut);

		System.out.println("输入密    码:" + password);
		edit_pwd.sendKeys(password);
		Thread.sleep(elementTimeOut);
		register_btn.click();

		Thread.sleep(elementTimeOut);

		String actualActivity = ((AndroidDriver) driver).currentActivity();
		System.out.println("当前页面Activity：" + actualActivity);

		if (actualActivity.equals(".activity.RegisterActivity")) {
			AssertActivty(actualActivity, expected);
			Thread.sleep(elementTimeOut);
			System.out.println("执行back键操作");
			Thread.sleep(elementTimeOut);
			((AndroidDriver) driver).pressKeyCode(BACK_CODE);
			Thread.sleep(elementTimeOut);
			((AndroidDriver) driver).pressKeyCode(BACK_CODE);
			Thread.sleep(elementTimeOut);
		} else {
			AssertActivty(actualActivity, expected);
			Thread.sleep(elementTimeOut);
			cmdCommand.excuteCmd(CLEARDATA);
			Thread.sleep(2000);
			cmdCommand.excuteCmd(LAUNCH_APP);
			Thread.sleep(1000);
		}

	}

	/**
	 * 点击注册进入注册页面
	 * 
	 * @description 注册准备工作
	 */
	public void beforeRegister() {
		WebElement startText = driver.findElement(WelcomeActivityElements.startText);
		startText.click();
		System.out.println("点击注册按钮跳转到注册页面");
		WebElement regist_btn = driver.findElement(LoginActivityElements.regist_btn);
		regist_btn.click();
		try {
			Thread.sleep(elementTimeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断言是否注册成功：注册失败--停留在注册页；注册成功--返回登录页
	 * 
	 * @param actual
	 * @param expect
	 */
	public void AssertActivty(String actual, String expect) {
		switch (actual) {
		case expectedRegiterFailed:
			System.out.println("****注册失败的case****");
			Assert.assertEquals(actual, expect);
			System.out.println("注册失败");
			break;
		case expectedRegiterSuccessed:
			System.out.println("****注册成功的case****");
			Assert.assertEquals(actual, expect);
			System.out.println("注册成功");
		default:
			break;
		}
	}

	/**
	 * 等待加载注册页面的UI
	 */
	@SuppressWarnings("unchecked")
	public void checkUiWithRegisterPage() {
		appiumUtils.waitForElementToLoad(driver, waitTimeout, RegisterActivityElements.usernameLabel);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, RegisterActivityElements.edit_username);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, RegisterActivityElements.passwordLabel);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, RegisterActivityElements.edit_password);
		appiumUtils.waitForElementToLoad(driver, waitTimeout, RegisterActivityElements.register_btn);
	}

}
