package com.ntut.killboss.sprite;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class SpriteBoss extends Sprite {

	private static final String TAG = "SpriteBoss";

	public SpriteBoss(GameView gameView, Bitmap bitmap) {
		_gameView = gameView;
		setBitmap(bitmap, 1, 1);
		_x = GameView._screenSize.x / 2;
		_y = GameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM;
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
