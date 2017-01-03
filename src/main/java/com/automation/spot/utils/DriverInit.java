package com.automation.spot.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverInit {
	public static AppiumDriver<? extends WebElement> driver;
	static String deviceName="iOS Emulator";

	public static void startDriver() throws MalformedURLException
	{
		if(deviceName.contains("iOS")){
			driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4725/wd/hub"), setCapabilities(deviceName));
		}
		else{
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4725/wd/hub"), setCapabilities(deviceName));
		}
		System.out.println( "Driver is started successfully.." );
		waitTime(10000);
	}
	
	public static DesiredCapabilities setCapabilities(String deviceName){
		String apkName="Spot.apk";
		String appPackage="com.verizon.npd.spot";
		String appActivity="com.verizon.npd.spot.views.ItemsActivity";
		DesiredCapabilities capabilities = null;
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "\\testdata\\apk");
		File app = new File(appDir, apkName);
		switch(deviceName){
		case "Android Emulator":
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.0");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			capabilities.setCapability("locale", "US");
			capabilities.setCapability("deviceReadyTimeout", "450");  //Timeout in seconds while waiting for device to become ready
			capabilities.setCapability("newCommandTimeout", "600000");		
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appActivity", appActivity);
			break;
		case "iOS Emulator":
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS"); 
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iOS Emulator");
			capabilities.setCapability("locale", "US");
			capabilities.setCapability("deviceReadyTimeout", "450");  //Timeout in seconds while waiting for device to become ready
			capabilities.setCapability("newCommandTimeout", "600000");		
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appActivity", appActivity);
			break;
			
		}
		return capabilities;
	}
	public static void waitTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
