package com.ntut.killboss.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;

import com.ntut.killboss.core.GameView;
import com.ntut.killboss.core.StartGameActivity;
import com.ntut.killboss.object.ObjectSkill;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	private int currentFrameWidth = 4;
	private int currentFrameHeight = 2;

	public SpriteHero(Context context, GameView gameView, Bitmap bitmap,
			Bitmap bitmapMirror) {

		bitmap = Bitmap.createScaledBitmap(bitmap, GameView._screenSize.x,
				GameView._screenSize.y, true);
		bitmapMirror = Bitmap.createScaledBitmap(bitmapMirror,
				GameView._screenSize.x, GameView._screenSize.y, true);

		_context = context;
		_gameView = gameView;
		this.setBitmap(bitmap, bitmapMirror, 10, 7);

		_y = GameView._screenSize.y - _height - GameView._screenSize.y / 6;

	}
	
	public void shutDown(){
		if (_direction) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 0;
					currentFrameHeight = 4;
				}
			}, 100);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 1;
					currentFrameHeight = 4;
				}
			}, 500);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 9;
					currentFrameHeight = 2;
				}
			}, 700);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 9;
					currentFrameHeight = 3;
				}
			}, 1500);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 9;
					currentFrameHeight = 4;
				}
			}, 100);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 8;
					currentFrameHeight = 4;
				}
			}, 500);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 0;
					currentFrameHeight = 2;
				}
			}, 700);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 0;
					currentFrameHeight = 3;
				}
			}, 1500);
		}
	}

	public void resetImage() {
		if (_direction) {
			currentFrameWidth = 4;
			currentFrameHeight = 2;
		} else {
			currentFrameWidth = 5;
			currentFrameHeight = 2;
		}
	}

	public void skillAgetReady() {

		if (_direction) {
			currentFrameWidth = 0;
			currentFrameHeight = 2;
		} else {
			currentFrameWidth = 9;
			currentFrameHeight = 2;
		}
	}

	public void skillAshot() {
		if (_direction) {
			currentFrameWidth = 2;
			currentFrameHeight = 2;
		} else {
			currentFrameWidth = 7;
			currentFrameHeight = 2;
		}
		int width = _direction ? _width : 0;
		Log.d(TAG, "width =" + width);

		ObjectSkill temp = new ObjectSkill(_context,
				GameView._objectSkillsHero, _x + width, _y + _height / 2,
				_direction);
		GameView._objectSkillsHero.add(temp);
	}

	public void skillBgetReady1() {
		if (_direction) {
			currentFrameWidth = 0;
			currentFrameHeight = 3;
		} else {
			currentFrameWidth = 9;
			currentFrameHeight = 3;
		}
	}

	public void skillBgetReady2() {
		if (_direction) {
			currentFrameWidth = 1;
			currentFrameHeight = 3;
		} else {
			currentFrameWidth = 8;
			currentFrameHeight = 3;
		}
	}

	public void skillBshot() {
		if (_direction) {
			currentFrameWidth = 2;
			currentFrameHeight = 3;
		} else {
			currentFrameWidth = 7;
			currentFrameHeight = 3;
		}

	}

	public void hurt() {

		if (_direction) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 4;
					currentFrameHeight = 5;
				}
			}, 50);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 3;
					currentFrameHeight = 5;
				}
			}, 300);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 2;
					currentFrameHeight = 5;
				}
			}, 500);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					resetImage();
				}
			}, 700);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 5;
					currentFrameHeight = 5;
				}
			}, 50);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 6;
					currentFrameHeight = 5;
				}
			}, 300);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					currentFrameWidth = 7;
					currentFrameHeight = 5;
				}
			}, 500);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() { // TODO Auto-generated method stub
					resetImage();
				}
			}, 750);
		}
	}

	public void jumpUp() {
		if (_direction) {
			currentFrameWidth = 2;
			currentFrameHeight = 4;
		} else {
			currentFrameWidth = 7;
			currentFrameHeight = 4;
		}
	}

	public void jumpDown() {
		if (_direction) {
			currentFrameWidth = 3;
			currentFrameHeight = 4;
		} else {
			currentFrameWidth = 6;
			currentFrameHeight = 4;
		}
	}

	public void flashRight(final int x) {
		_direction = true;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 5;
				currentFrameHeight = 6;
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 6;
				currentFrameHeight = 6;
			}
		}, 200);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 7;
				currentFrameHeight = 6;
			}
		}, 350);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				int tempX = _x + x;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else {
					_x = tempX;
				}
			}
		}, 400);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 6;
				currentFrameHeight = 6;
			}
		}, 600);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 700);

	}

	public void flashLeft(final int x) {
		_direction = false;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 4;
				currentFrameHeight = 6;
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 3;
				currentFrameHeight = 6;
			}
		}, 200);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 2;
				currentFrameHeight = 6;
			}
		}, 350);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				int tempX = _x - x;
				if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 400);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 3;
				currentFrameHeight = 6;
			}
		}, 600);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 700);
	}

	public void jumpRight() {
		_direction = true;

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 30;
				_y = tempY;
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 20;
				_y = tempY;
			}
		}, 150);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 15;
				_y = tempY;
			}
		}, 250);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 30;
				_y = tempY;
			}
		}, 350);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 20;
				_y = tempY;
			}
		}, 450);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 15;
				_y = tempY;
			}
		}, 550);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 650);
	}

	public void jumpLeft() {
		_direction = false;

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 30;
				_y = tempY;
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 20;
				_y = tempY;
			}
		}, 150);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 15;
				_y = tempY;
			}
		}, 250);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 30;
				_y = tempY;
			}
		}, 350);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 20;
				_y = tempY;
			}
		}, 450);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 15;
				_y = tempY;
			}
		}, 550);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 650);
	}

	public void moveRight(final int x) {
		_direction = true;

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 4;
				currentFrameHeight = 4;
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else {
					_x = tempX;
				}
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 5;
				currentFrameHeight = 4;
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else {
					_x = tempX;
				}
			}
		}, 250);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 6;
				currentFrameHeight = 4;
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else {
					_x = tempX;
				}
			}
		}, 450);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 650);
	}

	public void moveLeft(final int x) {
		_direction = false;
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 5;
				currentFrameHeight = 4;
				int tempX = _x - x / 3;
				if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 4;
				currentFrameHeight = 4;
				int tempX = _x - x / 3;
				if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 250);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				currentFrameWidth = 3;
				currentFrameHeight = 4;
				int tempX = _x - x / 3;
				if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 450);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() { // TODO Auto-generated method stub
				resetImage();
			}
		}, 650);
	}

	private Rect src = new Rect();
 
	@Override
	public void onDraw(Canvas canvas) {

		// dst.set(_x, _y, _x + _width, _y + _height);

		int srcX = currentFrameWidth * this._width;
		int srcY = currentFrameHeight * this._height;
		src.set(srcX, srcY, srcX + this._width, srcY + this._height);

		dst.set(_x, _y, _x + this._width, _y + this._height);

		if (_direction) {
			canvas.drawBitmap(_bitmap, src, dst, null);
		} else {
			canvas.drawBitmap(this._bitmapMirror, src, dst, null);

		}
		drawHP(canvas);
	}
}
