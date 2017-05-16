package com.xiao.simplebrowsertest.utils;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NoSuchContextException;

public class AppiumUtils {

	/**
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public WebElement findElement(AppiumDriver<WebElement> driver, By by) {
		return driver.findElement(by);
	}

	/**
	 * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
	 * 
	 * @param driver
	 * @param elementTimeOut
	 * @param By
	 */
	public void waitForElementToLoad(AppiumDriver<WebElement> driver, int elementTimeOut, final By By) {
		System.out.println("开始查找元素 [" + By + "]");
		try {
			(new WebDriverWait(driver, elementTimeOut)).until(new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(By);
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException e) {
			System.out.println("超时!! " + elementTimeOut + " 秒之后还没找到元素  [" + By + "]");
			Assert.fail("超时!! " + elementTimeOut + " 秒之后还没找到元素  [" + By + "]");

		}
		System.out.println("找到了元素  [" + By + "]");
	}

	/**
	 * 判断文本是不是和需求要求的文本一致
	 * 
	 * @param actual
	 * @param expected
	 */
	public boolean isTextCorrect(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (AssertionError e) {
			System.out.println("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
			Assert.fail("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
			return false;
		}
		System.out.println("找到了期望的文字: [" + expected + "]");
		return true;
	}

	/**
	 * toast判断
	 * 
	 * @param driver
	 * @param expectedToast
	 */
	public void searchToast(AppiumDriver<WebElement> driver, String expectedToast) {
		// toast 寻找测试
		try {
			final WebDriverWait wait = new WebDriverWait(driver, 2);
			Assert.assertNotNull(wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(".//*[contains(@text,'" + expectedToast + "')]"))));
			System.out.println("找到了toast");
		} catch (Exception e) {
			throw new AssertionError("找不到" + expectedToast);
		}
	}

	/**
	 * 清空元素内容
	 * @param element
	 */
	public void clear(AppiumDriver<WebElement> driver,By byElement){
		WebElement element = findElement(driver,byElement);
		try {
			System.out.println("清空"+byElement+"元素内容");
			element.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入内容
	 * @param driver
	 * @param byElement
	 * @param str
	 */
	public void typeContent(AppiumDriver<WebElement> driver,By byElement,String str){
		WebElement element = findElement(driver, byElement);
		System.out.println("在元素"+byElement+"中输入："+str);
		element.sendKeys(str);
	}
	
	/**
	 * 点击操作
	 * @param driver
	 * @param byElement
	 */
	public void click(AppiumDriver<WebElement> driver,By byElement){
		WebElement element = findElement(driver, byElement);
		try {
			System.out.println("点击元素"+byElement);
			element.click();
		} catch (Exception e) {
			Assert.fail("点击元素"+byElement+"失败", e);
		}
	}
	
	/**
	 * 获取文本1
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public String getText(AppiumDriver<WebElement> driver, By by) {
		return driver.findElement(by).getText().trim();
	}

	/**
	 * 获取文本2
	 * 
	 * @param driver
	 * @param locateWay
	 * @param locateValue
	 * @return
	 */
	public String getText(AppiumDriver<WebElement> driver, String locateWay, String locateValue) {
		String str = "";
		switch (locateWay) {

		case "AccessibilityId":
			str = driver.findElementByAccessibilityId(locateValue).getText().trim();
			break;
		// case "AndroidUIAutomator":
		// str =
		// driver.findElementByAndroidUIAutomator(locateValue).getText().trim();
		// break;
		case "ClassName":
			str = driver.findElementByClassName(locateValue).getText().trim();
			break;
		case "CSS":
			str = driver.findElementByCssSelector(locateValue).getText().trim();
			break;
		case "ID":
			str = driver.findElementById(locateValue).getText().trim();
			break;
		case "LinkText":
			str = driver.findElementByLinkText(locateValue).getText().trim();
			break;
		case "Name":
			str = driver.findElementByName(locateValue).getText().trim();
			break;
		case "PartialLinkText":
			str = driver.findElementByPartialLinkText(locateValue).getText().trim();
			break;
		case "TagName":
			str = driver.findElementByTagName(locateValue).getText().trim();
			break;
		case "Xpath":
			str = driver.findElementByXPath(locateValue).getText().trim();
			break;
		default:
			System.out.println("定位方式：" + locateWay + "不被支持");
			Assert.fail("定位方式：" + locateWay + "不被支持");

		}
		return str;

	}

	/**
	 * 转到webView页面
	 * 
	 * @param driver
	 * @param contextName
	 */
	public void switchWebview(AppiumDriver<WebElement> driver, String contextName) {
		try {
			Set<String> contexts = driver.getContextHandles();
			for (String context : contexts) {
				// 打印出来看看有哪些context
				System.out.println(context);

			}
			driver.context(contextName);
		} catch (NoSuchContextException nce) {
			System.out.printf("没有这个context:" + contextName, nce);
			Assert.fail("没有这个context:" + contextName, nce);
		}

	}

	/**
	 * 判断实际文本时候包含期望文本
	 * 
	 * @param actual
	 *            实际文本
	 * @param expect
	 *            期望文本
	 */
	public void isContains(String actual, String expect) {
		try {
			Assert.assertTrue(actual.contains(expect));
		} catch (AssertionError e) {
			System.err.println("The [" + actual + "] is not contains [" + expect + "]");
			Assert.fail("The [" + actual + "] is not contains [" + expect + "]");
		}
		System.err.println("The [" + actual + "] is contains [" + expect + "]");
	}

	/**
	 * 
	 * @param by
	 * 
	 * @param expect
	 * 
	 */
	public void isContains(By by, String expect) {
		String actual = by.toString();
		System.out.println("The Retrieved content is contains[" + expect + "]");
	}

}
