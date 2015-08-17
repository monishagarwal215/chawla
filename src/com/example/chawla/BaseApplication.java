package com.example.chawla;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;

/**
 * @author Monish Agarwal
 */
public class BaseApplication extends Application {
	public static int GENERAL_TRACKER = 0;

	public static Context mContext = null;
	private static boolean isGcmLoaded;
	private boolean isAppInVisible;
	private boolean isAppUpdateAvailable;

	public static boolean isGcmLoaded() {
		return isGcmLoaded;
	}

	public boolean isAppInVisible() {
		return isAppInVisible;
	}

	public void setAppInVisible(boolean isAppInVisible) {
		this.isAppInVisible = isAppInVisible;
	}

	public static void setGcmLoaded(boolean isGcmLoaded) {
		BaseApplication.isGcmLoaded = isGcmLoaded;

	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		initializeParse();
	}

	private void initializeParse() {
		Parse.enableLocalDatastore(getApplicationContext());
		Parse.initialize(this);
		Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
	}

}
