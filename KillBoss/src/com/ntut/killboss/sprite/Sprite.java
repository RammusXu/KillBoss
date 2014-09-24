package com.ntut.killboss.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;

public abstract class Sprite {
	private static final String TAG = "Sprite";
	// UI
	protected Context _context;
	protected GameView _gameView;
	protected Rect dst = new Rect();
	protected Paint paint = new Paint();

	// X,Y
	protected int _x = 0;
	protected int _speedX = 10;
	protected int _y = 0;
	protected int _speedY = 10;
	protected boolean _direction = true;

	// Bitmap
	protected Bitmap _bitmap;
	protected Bitmap _bitmapMirror;
	protected int _width;
	protected int _height;

	// Arguments
	protected int _hpInitail = 10;
	protected int _hp = 10;

	public abstract void onDraw(Canvas canvas);

	public int get_x() {
		return _x;
	}

	public int get_y() {
		return _y;
	}

	public int get_width() {
		return _width;
	}

	public int get_height() {
		return _height;
	}

	public boolean get_direction() {
		return _direction;
	}

	public boolean checkSpriteDie() {
		if (_hp <= 0) {
			return true;
		}
		return false;
	}

	private static final int HP_HEIGHT = 20;

	protected void drawHP(Canvas canvas) {
		int tempHP = (int) (_width * ((double) _hp / _hpInitail));
		if (tempHP < 0) {
			tempHP = 0;
		}

		dst.set(_x, _y - HP_HEIGHT, _x + tempHP, _y);

		// FILL
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(dst, paint);

		// STROKE
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);
		canvas.drawRect(dst, paint);
	}

	public void reduceHP(int hp) {
		_hp -= hp;
	}

	public void increaseHP(int hp) {
		_hp += hp;
	}

	public void move(int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		int tempX = _x + x;
		if (tempX > (GameView._screenSize.x - _width)) {
			_x = GameView._screenSize.x - _width;
		} else if (tempX < 0) {
			_x = 0;
		} else {
			_x = tempX;
		}
	}

	public Boolean checkObjectOutScreen(int nextX, int nextY) {

		if (nextX > (GameView._screenSize.x - _width) || _x < 0) {
			return true;
		}
		if (nextY > (GameView._screenSize.y - this._height)
				|| _y + _speedY <= 0) {
			return true;
		}

		return false;
	}

	public void knockOut(int x, int y) {
		_x += x;
		_y += y;
	}

	/**
	 * Set the Sprite source
	 * 
	 * @param bitmap
	 *            Bitmap
	 * @param columns
	 *            This bitmap contain how many columns.
	 * @param rows
	 *            This bitmap contain how many rows.
	 */
	public void setBitmap(Bitmap bitmap,Bitmap bitmapMirror, int columns, int rows) {
		_bitmap = bitmap;
		_bitmapMirror = bitmapMirror;
		_width = bitmap.getWidth() / columns;
		_height = bitmap.getHeight() / rows;
	}

	public void loadBitmap(int resID, int width, int height) {
		_bitmap = FunctionUtilities.createScaleBitmap(_context.getResources(),
				resID, width, height);
		_width = _bitmap.getWidth();
		_height = _bitmap.getHeight();
	}

	public void jump(int x) {
		// TODO Auto-generated method stub

	}
}
