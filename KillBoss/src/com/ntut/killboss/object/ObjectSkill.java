package com.ntut.killboss.object;

import java.util.List;

import com.ntut.killboss.core.HelloGameView;
import com.ntut.killboss.sprite.AnimationReduceHP;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

	public ObjectSkill(List<ObjectSkill> objectSkills, Bitmap bitmap, float x, float y) {
		//TEST   5
		//TEST 2
		//TEST 2
		//TEST 2
		_x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				HelloGameView._screenSize.x - bmp.getWidth());
		_y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
				HelloGameView._screenSize.y - bmp.getHeight());
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
		}
	}

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, _x, _y, null);

	}
}
