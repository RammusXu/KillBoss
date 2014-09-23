package com.ntut.killboss.sprite;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteBackground extends Sprite {

	public SpriteBackground(GameView gameView, Bitmap bitmap) {

		// bitmap = Bitmap.createScaledBitmap(bitmap, GameView._screenSize.x,
		// GameView._screenSize.y, true);

		_gameView = gameView;
		this.setBitmap(bitmap, 1, 1);

		_y = 0;
		_x = 0;

	}

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		dst.set(_x, _y, GameView._screenSize.x, GameView._screenSize.y
				- Constant.SPACE_TO_BOTTOM);

		canvas.drawBitmap(this._bitmap, null, dst, null);
	}

}
