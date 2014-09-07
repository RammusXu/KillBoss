package com.ntut.killboss;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.object.Sprite;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	HelloGameView _gameView;
	private boolean _direction = true;

	public SpriteHero(HelloGameView gameView, Bitmap bitmap, Point screenSize) {
		_gameView = gameView;
		setBitmap(bitmap, 1, 1);

		_y = screenSize.y - _height - Constant.SPACE_TO_BOTTOM;
		Log.e(TAG, "_y = " + _y);
		Log.e(TAG, "_screenWidth = " + HelloGameView._screenSize.x);
		// Log.e(TAG, "_screenHeight = " + HelloGameActivity._screenHeight);

	}

	//	TODO
	public void move(int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		if (!checkObjectOutScreen(_x + x, _y)) {
			_x += x;
		}
	}

	private Rect src = new Rect();
	private Rect dst = new Rect();

	@Override
	public void onDraw(Canvas canvas) {
		dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {

		}

		canvas.drawBitmap(_bitmap, null, dst, null);
	}

}
