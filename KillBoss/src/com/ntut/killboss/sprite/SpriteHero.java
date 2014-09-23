package com.ntut.killboss.sprite;

import android.R.color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;

public class SpriteHero extends Sprite {
	private static final String TAG = "SpriteHero";
	private int currentFrameWidth = 4;
	private int currentFrameHeight = 2;
	private Bitmap bimapHurt;

	public SpriteHero(GameView gameView, Bitmap bitmap) {

		bitmap = Bitmap.createScaledBitmap(bitmap, GameView._screenSize.x,
				GameView._screenSize.y, true);
//		bimapHurt =

		_gameView = gameView;
		this.setBitmap(bitmap, 10, 7);

		_y = GameView._screenSize.y - _height - GameView._screenSize.y / 6;

	}

	@Override
	public void move(final int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}

		// currentFrame = ++currentFrame % 10;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				heroWalk1();
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 50);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				heroWalk2();
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 150);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				heroWalk3();
				int tempX = _x + x / 3;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 250);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				resetImage();
			}
		}, 400);

	}

	public void slide(final int x) {
		slideReady1();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				slideReady2();
			}
		}, 300);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				slideReady3();
				int tempX = _x + x;
				if (tempX > (GameView._screenSize.x - _width)) {
					_x = GameView._screenSize.x - _width;
				} else if (tempX < 0) {
					_x = 0;
				} else {
					_x = tempX;
				}
			}
		}, 600);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				slideFinish3();
			}
		}, 900);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				resetImage();
			}
		}, 1100);
	}

	@Override
	public void jump(final int x) {
		if (x > 0) {
			_direction = true;
		} else {
			_direction = false;
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 20;
				_y = tempY;
			}
		}, 100);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 15;
				_y = tempY;
			}
		}, 200);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpUp();
				int tempY = _y - GameView._screenSize.y / 10;
				_y = tempY;
				_x = _x + x / 3;
			}
		}, 300);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 20;
				_y = tempY;
				_x = _x + x / 3;
			}
		}, 400);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 15;
				_y = tempY;
				_x = _x + x / 7;
			}
		}, 500);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				jumpDown();
				int tempY = _y + GameView._screenSize.y / 10;
				_y = tempY;
			}
		}, 600);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				resetImage();
			}
		}, 700);
	}

	public void resetImage() {
		currentFrameWidth = 4;
		currentFrameHeight = 2;
	}

	public void skillAgetReady() {
		currentFrameWidth = 0;
		currentFrameHeight = 2;
	}

	public void skillAshot() {
		currentFrameWidth = 2;
		currentFrameHeight = 2;
	}

	public void skillBgetReady1() {
		currentFrameWidth = 7;
		currentFrameHeight = 0;
	}

	public void skillBgetReady2() {
		currentFrameWidth = 4;
		currentFrameHeight = 3;
	}

	public void skillBshot() {
		currentFrameWidth = 8;
		currentFrameHeight = 0;
	}

	public void jumpUp() {
		currentFrameWidth = 2;
		currentFrameHeight = 4;
	}

	public void jumpDown() {
		currentFrameWidth = 3;
		currentFrameHeight = 4;
	}

	public void heroWalk1() {
		currentFrameWidth = 7;
		currentFrameHeight = 3;
	}

	public void heroWalk2() {
		currentFrameWidth = 5;
		currentFrameHeight = 3;
	}

	public void heroWalk3() {
		currentFrameWidth = 6;
		currentFrameHeight = 3;
	}

	private void slideReady1() {
		// TODO Auto-generated method stub
		currentFrameWidth = 5;
		currentFrameHeight = 6;
	}

	private void slideReady2() {
		// TODO Auto-generated method stub
		currentFrameWidth = 6;
		currentFrameHeight = 6;
	}

	private void slideReady3() {
		// TODO Auto-generated method stub
		currentFrameWidth = 7;
		currentFrameHeight = 6;
	}

	private void slideFinish3() {
		// TODO Auto-generated method stub
		currentFrameWidth = 6;
		currentFrameHeight = 6;
	}

	private Rect src = new Rect();

	int lifeCycle = 20;	//system time

	protected void move() {
		lifeCycle -= 1;
		Log.d("DEBUG", "lifeCycle =" + lifeCycle);
		if (lifeCycle <20) {
			heroWalk1();
//			status = normal;
		} else if (lifeCycle < 10) {
			// change status
		} else if (lifeCycle < 1) {
			// change status
		} 
	}

	@Override
	public void onDraw(Canvas canvas) {
//		move(); TODO 
		// dst.set(_x, _y, _x + _width, _y + _height);

		int srcX = currentFrameWidth * this._width;
		int srcY = currentFrameHeight * this._height;
		src.set(srcX, srcY, srcX + this._width, srcY + this._height);

		// 改成隨著螢幕大小改變
		// _x + _width, _y + _height
		// _x + GameView._screenSize.x/(看要幾倍)
		// _y + GameView._screenSize.y/(看要幾倍)
		dst.set(_x, _y, _x + this._width, _y + this._height);

		// //status = run
		// if(status) {
		//
		// canvas.drawBitmap(this._bitmap, src, dst, null);
		// //status = slide
		// } else if() {
		//
		// canvas.drawBitmap(this.bitmapHurt, src, dst, null);
		// //status = hurt
		// }
		canvas.drawBitmap(this._bitmap, src, dst, null);
		drawHP(canvas);
		// TEEST
	}

}
