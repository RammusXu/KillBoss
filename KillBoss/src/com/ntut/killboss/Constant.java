package com.ntut.killboss;

import android.app.Application;

public class Constant extends Application {

	public static final int SPACE_TO_BOTTOM = 48;

	private int _screenWidth = 0;
	private int _screenHeight = 0;

	public int getScreenWidth() {
		return _screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		_screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return _screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		_screenHeight = screenHeight;
	}
}
