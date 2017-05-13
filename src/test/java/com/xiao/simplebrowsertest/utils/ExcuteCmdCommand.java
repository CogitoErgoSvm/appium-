package com.xiao.simplebrowsertest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExcuteCmdCommand {
	
	/**
	 * 执行cmd命令
	 * @param commandStr
	 */
	public void excuteCmd(String commandStr) {
		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec(commandStr);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			System.out.println(stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
