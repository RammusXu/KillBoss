package com.ntut.killboss;

import java.util.Random;

import com.ntut.killboss.object.Sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Sprite3x4 extends Sprite {
	private HelloGameView _gameView;

	private int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
	private static final int MAX_SPEED = 5;

	private static final String TAG = "Sprite3x4";

	private int intROWs = 4;
	private int intCOLUMNs = 3;
	private int currentFrame = 0;
	private int flagDirection = 1;

	public Sprite3x4(HelloGameView gameView, Bitmap bitmap) {
		this._gameView = gameView;
		setBitmap(bitmap, intCOLUMNs, intROWs);

		this._speedX = getRand(10, 21);
		Random rnd = new Random();
		this._speedX = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		this._speedY = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		
	}

	private void update() {
		// Set X,Y
		if (_x > (_gameView.getWidth() - _width) || _x < 0) {
			_speedX = -_speedX;
		}
		_x = _x + _speedX;
		if (_y > (_gameView.getHeight() - this._height) || _y + _speedY <= 0) {
			_speedY = -_speedY;
		}
		_y = _y + _speedY;

		currentFrame = ++currentFrame % intCOLUMNs;

		if (_speedX > 0) {
			flagDirection = 2;
		} else {
			flagDirection = 1;
		}
	}

	public boolean isTouched(float x2, float y2) {
		return x2 > _x && x2 < (_x + this._width) && y2 > _y
				&& y2 < (_y + this._height);

	}

	Rect src = new Rect();
	Rect dst = new Rect();

	@Override
	public void onDraw(Canvas canvas) {
		update();

		int srcX = currentFrame * this._width; // 0,1,2
		int srcY = getAnimationRow() * this._height;
		src.set(srcX, srcY, srcX + this._width, srcY + this._height);
		dst.set(_x, _y, _x + this._width, _y + this._height);
		canvas.drawBitmap(_bitmap, src, dst, null);

	}

	private int getRand(int min, int max) {
		// 0,1,2
		// int min = 0;
		// int max = 3;
		Random random = new Random();
		int randomNumber = random.nextInt(max - min) + min;
		return randomNumber;
	}

	private int getAnimationRow() {
		double dirDouble = (Math.atan2(_speedX, _speedY) / (Math.PI / 2) + 2);
		int direction = (int) Math.round(dirDouble) % intROWs;
		return DIRECTION_TO_ANIMATION_MAP[direction];
	}

}
