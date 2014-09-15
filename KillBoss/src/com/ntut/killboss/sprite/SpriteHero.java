package com.ntut.killboss.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.GameView;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	private int currentFrameWidth = 4;
	private int currentFrameHeight = 2;

	public SpriteHero(GameView gameView, Bitmap bitmap) {
		_gameView = gameView;
		this.setBitmap(bitmap, 10, 7);

		_y = GameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM;

	}

	@Override
	public void move(int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		//currentFrame = ++currentFrame % 10;
		if(currentFrameWidth == 4 && currentFrameHeight == 2)
		{
			currentFrameWidth = 6;
			currentFrameHeight = 3;
		}
		
		else if (currentFrameWidth == 6 && currentFrameHeight == 3)
		{
			currentFrameWidth = 5;
			currentFrameHeight = 3;
		}
		else if (currentFrameWidth == 5 && currentFrameHeight == 3)
		{
			currentFrameWidth = 6;
			currentFrameHeight = 3;
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
	public void resetImage(){
		currentFrameWidth = 4;
		currentFrameHeight = 2;
	}
	public void skillAgetReady(){
			currentFrameWidth = 0;
		    currentFrameHeight = 2;
	}
	public void skillAshot(){
		currentFrameWidth = 2;
		currentFrameHeight = 2;
	}

	@Override
	public void onDraw(Canvas canvas) {
		// dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {

		}
		int srcX = currentFrameWidth * this._width;
		int srcY = currentFrameHeight * this._height;
		Rect src = new Rect(srcX, srcY, srcX + this._width, srcY + this._height);
		Rect dst = new Rect(_x, _y, _x + this._width, _y + this._height);
		canvas.drawBitmap(this._bitmap, src, dst, null);

		// canvas.drawBitmap(_bitmap, null, dst, null);
		drawHP(canvas);
	}

}
