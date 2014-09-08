package com.ntut.killboss.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.HelloGameView;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";

	public SpriteHero(HelloGameView gameView, Bitmap bitmap) {
		_gameView = gameView;
		setBitmap(bitmap, 1, 1);

		_y = HelloGameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM;

	}

	private Rect src = new Rect();

	@Override
	public void onDraw(Canvas canvas) {
		dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {

		}

		canvas.drawBitmap(_bitmap, null, dst, null);
		drawHP(canvas);
	}

}
