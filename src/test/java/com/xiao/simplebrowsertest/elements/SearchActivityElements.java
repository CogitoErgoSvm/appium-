package com.xiao.simplebrowsertest.elements;

import org.openqa.selenium.By;

/**
 * 
 * @author yx
 * @description App搜索页元素
 *
 */
public class SearchActivityElements {
	
	/* "返回首页"按钮 */
	public static final By Home = By.id("com.xiao.simplebrowser:id/home");
	
	/* "URL"输入框 */
	public static final By edit_netAddress = By.id("com.xiao.simplebrowser:id/net_address");
	
	/*"进度条"*/
	public static final By progressBar = By.id("com.xiao.simplebrowser:id/mProgress");
	
	/*WebView*/
	public static final By webView = By.id("com.xiao.simplebrowser:id/web");
	public static final By WEBVIEW = By.xpath("//android.webkit.WebView[contains(@index,2)]");
	
	/*WebView名字*/
	public static final String WEBVIEW_NAME = "WEBVIEW_com.xiao.simplebrowser";
	
	/* "搜索"按钮 */
	public static final By startSearch = By.id("com.xiao.simplebrowser:id/start");
	
}
