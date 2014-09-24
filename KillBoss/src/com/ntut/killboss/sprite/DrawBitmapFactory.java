package com.ntut.killboss.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.core.GameView;

public class DrawBitmapFactory {

	private Rect src = new Rect();
	private Rect dst = new Rect();
	private Bitmap _bitmap;
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	private int _columns;
	private int _frameNum;

	public DrawBitmapFactory(Bitmap bitmap, int x, int y, int columns,
			int rows, int frameNum) {
		_bitmap = bitmap;

		_x = x;
		_y = y;
		_width = bitmap.getWidth() / columns;
		_height = bitmap.getHeight() / rows;
		_columns = columns;
		_frameNum = frameNum;
	}

	int nowColumn = 0;
	int nowRow = 0;
	int nowFrame = 0;

	int changeCycle = 10;

	private static final int CHANGE_FRAME_SPEED = 1;
	private int frameChangeFlag = 0;

	private void update() {
		frameChangeFlag = (frameChangeFlag++) % CHANGE_FRAME_SPEED;
		if (frameChangeFlag < 1) {
			// changeCycle = 10;
			if (nowFrame < _frameNum) {
				nowFrame++;
				Log.d("DEBUG", "nowFrame = " + nowFrame);
				nowColumn = nowFrame % _columns;
				nowRow = (int) (nowFrame / _columns);
			}
		}
		// else {
		// changeCycle--;
		// }
	}

	public void onDraw(Canvas canvas) {
		update();

		int srcX = nowColumn * this._width;
		int srcY = nowRow * this._height;
		src.set(srcX, srcY, srcX + this._width, srcY + this._height);
		dst.set(_x, _y, _x + GameView._screenSize.x / 10, _y
				+ GameView._screenSize.y / 10);
		canvas.drawBitmap(_bitmap, src, dst, null);
	}

	public boolean checkEndAnimation() {
		if (nowFrame < _frameNum) {
			return false;
		}
		return true;
	}

	public void reset(int x , int y) {
		nowFrame = 0;
		_x = x;
		_y = y;
	}

}
