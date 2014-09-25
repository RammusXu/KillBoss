package com.ntut.killboss.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.R;
import com.ntut.killboss.core.GameView;

public class SpriteGameResult {
	Bitmap _bitmapVictory;
	Bitmap _bitmapDefeat;
	GameView _gameView;
	int _x;
	int _y;

	public SpriteGameResult(Context context, GameView gameView) {

		_bitmapVictory = FunctionUtilities.createScaleBitmap(
				context.getResources(), R.drawable.result_victory,
				GameView._screenSize.x / 2, GameView._screenSize.y
						- GameView._screenSize.y / 2);

		_bitmapDefeat = FunctionUtilities.createScaleBitmap(
				context.getResources(), R.drawable.result_defeat,
				GameView._screenSize.x / 2, GameView._screenSize.y
						- GameView._screenSize.y / 2);

		_gameView = gameView;

		_x = GameView._screenSize.x / 4;
		_y = GameView._screenSize.y / 4;
	}

	public void onDraw(Canvas canvas, boolean ifWin) {

		if (ifWin) {
			canvas.drawBitmap(_bitmapVictory, _x, _y, null);
		} else {
			canvas.drawBitmap(_bitmapDefeat, _x, _y, null);
		}
	}

	public boolean isTouched(float f, float g) {
		if (f > _x && f < (_x + _bitmapVictory.getWidth()) && g > _y
				&& g < (_y + _bitmapVictory.getHeight())) {
			return true;
		}
		return false;
	}
}
