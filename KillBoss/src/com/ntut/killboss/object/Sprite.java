package com.ntut.killboss.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Sprite {
	// X,Y
	protected int _x = 0;
	protected int _speedX = 10;
	protected int _y = 0;
	protected int _speedY = 10;

	// Bitmap
	protected Bitmap _bitmap;
	protected int _width;
	protected int _height;

	public abstract void onDraw(Canvas canvas);

	public void setBitmap(Bitmap bitmap, int columns, int rows) {
		_bitmap = bitmap;
		_width = bitmap.getWidth() / columns;
		_height = bitmap.getHeight() / rows;
	}
}
