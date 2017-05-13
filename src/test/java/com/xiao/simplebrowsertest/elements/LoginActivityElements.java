package com.xiao.simplebrowsertest.elements;

import org.openqa.selenium.By;

/**
 * 
 * @author yx
 * @description APP登录页元素
 */
public class LoginActivityElements {
	/*"用户名"标签*/
	public static final By usernameLabel = By.id("com.xiao.simplebrowser:id/username_label");
	/*"密码"标签*/
	public static final By passwordLabel = By.id("com.xiao.simplebrowser:id/password_label");
	/*"用户名"输入框*/
	public static final By edit_username = By.id("com.xiao.simplebrowser:id/username");
	/*"密码"输入框*/
	public static final By edit_password = By.id("com.xiao.simplebrowser:id/password");
	/*"登录"按钮*/
	public static final By login_btn = By.id("com.xiao.simplebrowser:id/login_button");
	/*"注册"按钮*/
	public static final By regist_btn = By.id("com.xiao.simplebrowser:id/register_button");
}
