package com.ntut.killboss.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.HelloGameView;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	private int currentFrame;

	public SpriteHero(HelloGameView gameView, Bitmap bitmap) {
		_gameView = gameView;
		this.setBitmap(bitmap, 10, 7);

		_y = HelloGameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM;

	}

	@Override
	public void move(int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		currentFrame = ++currentFrame % 10;
		int tempX = _x + x;
		if (tempX > (HelloGameView._screenSize.x - _width)) {
			_x = HelloGameView._screenSize.x - _width;
		} else if (tempX < 0) {
			_x = 0;
		} else {
			_x = tempX;
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		// dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {

		}

		int srcX = currentFrame * this._width;
		int srcY = 1 * this._height;
		Rect src = new Rect(srcX, srcY, srcX + this._width, srcY + this._height);
		Rect dst = new Rect(_x, _y, _x + this._width, _y + this._height);
		canvas.drawBitmap(this._bitmap, src, dst, null);

		// canvas.drawBitmap(_bitmap, null, dst, null);
		drawHP(canvas);
	}

}
