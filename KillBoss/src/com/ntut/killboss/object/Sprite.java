package com.ntut.killboss.object;

import com.ntut.killboss.HelloGameView;

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

	//
	protected HelloGameView _gameView;

	public abstract void onDraw(Canvas canvas);

	public Boolean checkObjectOutScreen(int nextX, int nextY) {

		if (nextX > (HelloGameView._screenSize.x - _width) || _x < 0) {
			return true;
		}
		if (nextY > (HelloGameView._screenSize.y - this._height) || _y + _speedY <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * Set the Sprite source
	 * @param bitmap Bitmap
	 * @param columns This bitmap contain how many columns.
	 * @param rows This bitmap contain how many rows.
	 */
	public void setBitmap(Bitmap bitmap, int columns, int rows) {
		_bitmap = bitmap;
		_width = bitmap.getWidth() / columns;
		_height = bitmap.getHeight() / rows;
	}
}
