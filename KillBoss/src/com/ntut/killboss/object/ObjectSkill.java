package com.ntut.killboss.object;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.Sprite;
import com.ntut.killboss.sprite.SpriteBoss;

public class ObjectSkill {
	private List<ObjectSkill> _objectSkills;
	private Bitmap bmp;

	// Arguments
	private int _x;
	private int _y;
	private int _damage = 1;
	private int _distance = 500;
	private int _speed = 10;
	private boolean _direction = true;

	private Paint paint = new Paint();

	public ObjectSkill(Context context, List<ObjectSkill> objectSkills,
			int resID, float x, float y) {

		bmp = FunctionUtilities.createBitmap(context.getResources(), resID);
		_x = (int) Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				GameView._screenSize.x - bmp.getWidth());
		_y = (int) Math.min(Math.max(y - bmp.getHeight() / 2, 0),
				GameView._screenSize.y - bmp.getHeight());
		_objectSkills = objectSkills;

	}

	private void update() {
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

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, _x, _y, null);

	}

	public void hitBoss(Sprite sprite, GameView gameView) {

		if (isCollsionWithRect(_x, _y, bmp.getWidth(), bmp.getHeight(),
				sprite.get_x(), sprite.get_y(), sprite.get_width(),
				sprite.get_height())) {
			_objectSkills.remove(this);
			sprite.reduceHP(_damage);
			GameView._temps.add(new AnimationReduceHP(gameView,
					GameView._temps, sprite.get_x(), sprite.get_y(), -_damage));
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
}
