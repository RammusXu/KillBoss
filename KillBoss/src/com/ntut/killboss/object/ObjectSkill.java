package com.ntut.killboss.object;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.SpriteBoss;

public class ObjectSkill {
	private List<ObjectSkill> _objectSkills;
	private Bitmap bmp;

	// Arguments
	private float _x;
	private float _y;
	private int _damage = 1;
	private int _distance = 500;
	private int _speed = 10;
	private boolean _direction = true;

	private Paint paint = new Paint();

	public ObjectSkill(Context context, List<ObjectSkill> objectSkills,
			int resID, float x, float y) {

		bmp = FunctionUtilities.createBitmap(context.getResources(), resID);
		_x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				GameView._screenSize.x - bmp.getWidth());
		_y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
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

	public void hitBoss(SpriteBoss boss, GameView gameView) {
		boolean b1 = _x > boss.get_x();
		boolean b2 = _x < boss.get_x() + boss.get_width();
		boolean b3 = _y > boss.get_y();
		boolean b4 = _y < boss.get_y() + boss.get_height();
		if (b1 && b2 && b3 && b4) {
			_objectSkills.remove(this);
			boss.reduceHP(_damage);
			GameView._temps.add(new AnimationReduceHP(gameView, GameView._temps, boss.get_x(), boss.get_y(), bmp, -_damage));
		}
	}
}
