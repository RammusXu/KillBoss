package com.ntut.killboss.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ntut.killboss.core.GameView;

public class SpriteBackground extends Sprite {

	public SpriteBackground(GameView gameView, Bitmap bitmap) {

		bitmap = Bitmap.createScaledBitmap(bitmap, GameView._screenSize.x,
				GameView._screenSize.y, true);

		_gameView = gameView;
		this.setBitmap(bitmap, null, 1, 1);

		_y = 0;
		_x = 0;

	}

	@Override
	public void onDraw(Canvas canvas) {

		dst.set(_x, _y, GameView._screenSize.x, GameView._screenSize.y
				- GameView._screenSize.y / 6);

		canvas.drawBitmap(this._bitmap, null, dst, null);
	}

}
