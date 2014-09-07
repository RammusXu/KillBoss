package com.ntut.killboss;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.ntut.killboss.object.Sprite;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	HelloGameView _gameView;
	private boolean _direction = true;

	public SpriteHero(HelloGameView gameView, Bitmap bitmap) {
		_gameView = gameView;
		setBitmap(bitmap, 1, 1);

		Log.e(TAG, "_y = " + _y);
		Log.e(TAG, "gameView.getHeight() = " + _gameView.getHeight());
		Log.e(TAG, "_height" + _height);
		_y = _gameView.getHeight() - _height;

		Log.e(TAG, "_y = " + _y);

	}

	public void move(int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		_x += x;
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
