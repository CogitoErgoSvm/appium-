package com.xiao.simplebrowsertest.testcases;

import java.io.IOException;

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(dataProvider = "registData")
	public void registerWithData(String username, String password, String expected) throws InterruptedException {
		System.out.println("****Start  testWithRegister****");
		beforeRegister();
		checkUiWithRegisterPage();
		//清空
		appiumUtils.clear(driver, RegisterActivityElements.edit_username);
		appiumUtils.clear(driver, RegisterActivityElements.edit_password);
		//输入
		appiumUtils.typeContent(driver, RegisterActivityElements.edit_username, username);
		Thread.sleep(elementTimeOut);
		appiumUtils.typeContent(driver, RegisterActivityElements.edit_password, password);
		Thread.sleep(elementTimeOut);
		//点击注册
		appiumUtils.click(driver, RegisterActivityElements.register_btn);
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
	 * @throws InterruptedException
	 * 
	 * @description 注册准备工作
	 */
	@SuppressWarnings("unchecked")
	public void beforeRegister() throws InterruptedException {

		appiumUtils.click(driver, WelcomeActivityElements.startText);
		System.out.println("点击注册按钮跳转到注册页面");
		Thread.sleep(elementTimeOut);
		appiumUtils.click(driver, LoginActivityElements.regist_btn);
		Thread.sleep(elementTimeOut);

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
