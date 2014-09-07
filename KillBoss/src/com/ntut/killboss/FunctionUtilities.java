package com.ntut.killboss;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class FunctionUtilities {

	private static final String TAG = "FunctionUtilities";

	public static Point getDisplaySize(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		Log.d(TAG, "X= " + size.x + "Y= " + size.y);
		return size;
	}
}
