package com.ntut.killboss;

import com.ntut.killboss.core.GameView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class FunctionUtilities {

	private static final String TAG = "FunctionUtilities";

	public static Bitmap createBitmap(Resources resource, int id) {
		Bitmap bmp = BitmapFactory.decodeResource(resource, id);
		return bmp;
	}

	public static Bitmap createScaleBitmap(Resources resource, int id,
			int width, int height) {

		Bitmap bmp = BitmapFactory.decodeResource(resource, id);
		bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
		return bmp;
	}

	public static Point getDisplaySize(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		Log.d(TAG, "X= " + size.x + "Y= " + size.y);
		return size;
	}

	public static Bitmap mirrorBitmap2(Bitmap a, int width, int height) {
		int w = a.getWidth();
		int h = a.getHeight();
		Bitmap newb = Bitmap.createBitmap(width, height, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		Canvas cv = new Canvas(newb);
		Matrix m = new Matrix();
		// m.postScale(1, -1); // 镜像垂直翻转
		m.postScale(-1, 1); // 镜像水平翻转
		// m.postRotate(-90); // 旋转-90度
		Bitmap new2 = Bitmap.createBitmap(a, 0, 0, w, h, m, true);
		cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),
				new Rect(0, 0, width, height), null);
		return newb;
	}

	public static int getRandom(int i) {
		return (int) (Math.random() * i);
	}
}
