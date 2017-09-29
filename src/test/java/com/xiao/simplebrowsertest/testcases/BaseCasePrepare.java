package com.xiao.simplebrowsertest.testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;

import com.xiao.simplebrowsertest.utils.AppiumUtils;
import com.xiao.simplebrowsertest.utils.ExcuteCmdCommand;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

/**
 * 
 * @author yx
 *
 */

public class BaseCasePrepare {

	// AppiumDriver对象
	@SuppressWarnings({ "rawtypes" })
	public AppiumDriver driver = null;

	// 建立Appium封装类对象
	public AppiumUtils appiumUtils = new AppiumUtils();

	// cmd实例对象
	public ExcuteCmdCommand cmdCommand = new ExcuteCmdCommand();

	// 清除应用数据cmd
	public static final String CLEARDATA = "adb shell pm clear com.xiao.simplebrowser";

	// 启动应用cmd
	public static final String LAUNCH_APP = "adb shell am start com.xiao.simplebrowser/.activity.WelcomeFlashActivity";

	// 杀掉应用进程cmd
	public static final String KILL_APP = "adb shell am force-stop com.xiao.simplebrowser";

	// 操作间隔时间，单位： ms
	public static int elementTimeOut = 300;

	// 控件加载超时时间，单位：s
	public static int waitTimeout = 20;

	// BACK键对应的Code值
	public static final int BACK_CODE = 4;

	// 获取工程路径
	public final File classpathRoot = new File(System.getProperty("user.dir"));

	// 初始化待测APP路径
	public final File appDir = new File(classpathRoot, "app");
	public final File app = new File(appDir, "simplebrowser.apk");

	// 初始化测试数据文件路径
	public final File excelDir = new File(classpathRoot, "data");
	public final File excel = new File(excelDir, "testData.xls");
	public final String path = excel.getAbsolutePath();

	/**
	 * @description setUp()：测试执行配置信息
	 * @throws MalformedURLException
	 */
	@BeforeClass
	public void setUp() {

		/**
		 * 建立session,测试信息初始化 测试平台（Android || ios） 平台系统版本（Android操作系统版本，ios系统版本）
		 * 待测App的package和MainActivity
		 */
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "63a7b810");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		capabilities.setCapability("appPackage", "com.xiao.simplebrowser");
		capabilities.setCapability("appActivity", "com.xiao.simplebrowser.activity.WelcomeFlashActivity");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		// 初始化driver，同Selenium初始化类似
		try {
			// driver = new AndroidDriver<WebElement>(new
			// URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			// driver = new AppiumDriver(newURL("http://127.0.0.1:4723/wd/hub"),
			// capabilities);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		// driver.close();
		driver.quit();
	}

}
