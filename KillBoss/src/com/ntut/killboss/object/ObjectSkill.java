package com.ntut.killboss.object;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.Sprite;

public class ObjectSkill {
	protected List<ObjectSkill> _objectSkills;
	protected Bitmap bmp;
	protected Bitmap bmpMirror;
	protected int _width;
	protected int _height;

	// Arguments
	protected int _x;
	protected int _y;
	protected int _damage = 1;
	private static final int INITIAL_DISTANCE = 500;
	protected int _distance = INITIAL_DISTANCE;
	protected int _speed = 10;
	protected boolean _direction = true;

	public void set_direction(boolean _direction) {
		this._direction = _direction;
	}

	private Paint paint = new Paint();

	public ObjectSkill(Context context, List<ObjectSkill> objectSkills,
			float x, float y, boolean direction) {
		this(context, objectSkills, Constant.skillDrawbleResourceIDs[0], x, y,
				direction);

	}

	protected Context _context;

	public ObjectSkill(Context context, List<ObjectSkill> objectSkills,
			Integer resID, float x, float y, boolean direction) {
		_context = context;

		changeBitmap(resID);

		_x = (int) Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				GameView._screenSize.x - bmp.getWidth());
		_y = (int) Math.min(Math.max(y - bmp.getHeight() / 2, 0),
				GameView._screenSize.y - bmp.getHeight());
		_objectSkills = objectSkills;
		_direction = direction;

	}

	protected void update() {
		if (_distance < 1) {
			_objectSkills.remove(this);
		} else {
			if (_direction) {
				_x += _speed;
			} else {
				_x -= _speed;
			}
			_distance -= _speed;
		}
	}

	protected boolean checkYOutOfBound() {
		if (_y > GameView._screenSize.y - GameView._bottomSpace
				- bmp.getHeight()) {
			return true;
		}
		return false;
	}

	public void onDraw(Canvas canvas) {
		update();

		if (_direction) {
			canvas.drawBitmap(bmp, _x, _y, null);
		} else {
			canvas.drawBitmap(bmpMirror, _x, _y, null);
		}

	}

	public void hitSprite(Sprite sprite, GameView gameView) {
		if (isCollsionWithRect2(_x, _y, bmp.getWidth(), bmp.getHeight(),
				sprite.get_x(), sprite.get_y(), sprite.get_width(),
				sprite.get_height())) {
			sprite.reduceHP(_damage);
			if (sprite.get_x() > _x) {
				sprite.knockOut(20, 0);
			} else {
				sprite.knockOut(-20, 0);
			}
			GameView._animationReduceHP.add(new AnimationReduceHP(gameView,
					sprite.get_x() + (sprite.get_width() / 2), sprite.get_y()
							+ (sprite.get_height() / 3), -_damage));

			_objectSkills.remove(this);
		}
	}

	public boolean isCollsionWithRect(int x1, int y1, int w1, int h1, int x2,
			int y2, int w2, int h2) {
		// 当矩形1位于矩形2的左侧
		if (x1 >= x2 && x1 >= x2 + w2) {
			return false;
			// 当矩形1位于矩形2的右侧
		} else if (x1 <= x2 && x1 + w1 <= x2) {
			return false;
			// 当矩形1位于矩形2的上方
		} else if (y1 >= y2 && y1 >= y2 + h2) {
			return false;
		} else if (y1 <= y2 && y1 + h1 <= y2) {
			return false;
		}
		// 所有不会发生碰撞都不满足时，肯定就是碰撞了
		return true;
	}

	public boolean isCollsionWithRect2(int x1, int y1, int w1, int h1, int x2,
			int y2, int w2, int h2) {

		if (x1 + w1 / 2 > x2 && x1 + w1 / 2 < x2 + w2 && y1 + h1 / 2 > y2
				&& y1 + h1 / 2 < y2 + h2) {
			return true;

		}
		return false;
	}

	protected void changeBitmap(Integer resID) {
		bmp = FunctionUtilities
				.createScaleBitmap(_context.getResources(), resID,
						GameView._screenSize.x / 10,
						GameView._screenSize.y / 10);
		bmpMirror = FunctionUtilities.mirrorBitmap2(bmp, bmp.getWidth(),
				bmp.getHeight());

		_width = bmp.getWidth();
		_height = bmp.getHeight();
	}

	public void activeObject(int x, int y, boolean direction) {
		_x = x;
		_y = y;
		_distance = INITIAL_DISTANCE;
		_direction = direction;
		_objectSkills.add(this);
	}

	public void activeObject() {
		_x = FunctionUtilities.getRandom(GameView._screenSize.x);
		_y = 0;
		_objectSkills.add(this);
	}
}
