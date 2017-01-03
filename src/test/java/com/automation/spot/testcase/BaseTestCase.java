package com.automation.spot.testcase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.automation.spot.pages.LaunchScreen;
import com.automation.spot.pages.LoginScreen;
import com.automation.spot.utils.DriverInit;



public class BaseTestCase {
	private static AppiumDriverLocalService serviceClient;
	LaunchScreen launchScreen;
	LoginScreen loginScreen;
	
	@BeforeSuite
	public void beforeSuite() throws MalformedURLException{
		startAppium();
		System.out.println( "Starting suite" );
	}
	
	@AfterSuite
	public void afterSuite() throws MalformedURLException{
		serviceClient.stop();
		System.out.println( "Stopping suite" );
	}
	
	@BeforeTest
	public void initScreens() throws MalformedURLException{
		//start driver
		DriverInit.startDriver();
				
		launchScreen=new LaunchScreen();
		loginScreen=new LoginScreen();	
	}

	public static void startAppium() {
		String osName = System.getProperty("os.name").toLowerCase();

		String nodePath = null;
		String appiumPath = null;

		if (osName.contains("mac")) {
			//            mac path
			nodePath = "/usr/local/bin/node";
			appiumPath = "/Users/pravendra/node_modules/appium/build/lib/main.js";
		} else if (osName.contains("linux")) {
			//      linux path
			nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
			appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
		}
		else if(osName.contains("windows")){
			//          windows path
			nodePath = "C:\\Program Files (x86)\\Appium\\node.exe";
			appiumPath = "C:\\Program Files (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js";
		}
		serviceClient = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		.usingDriverExecutable(new File(nodePath))
		.usingPort(4725)
		.withAppiumJS(new File(appiumPath)));

		serviceClient.start();
	}

}