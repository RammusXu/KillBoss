package com.ntut.killboss.object;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.Sprite;

public class ObjectSkill3 extends ObjectSkill {

	public ObjectSkill3(Context context, List<ObjectSkill> objectSkills, int x,
			int y, boolean direction) {

		super(context, objectSkills, Constant.skillDrawbleResourceIDs[2], x, y,
				direction);
		_x = (int) Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				GameView._screenSize.x - bmp.getWidth());
		_y = (int) Math.min(Math.max(y - bmp.getHeight() / 2, 0),
				GameView._screenSize.y - bmp.getHeight());
		_distance = 10; // life cycle
	}

	@Override
	protected void update() {
		if (_distance < 1) {
			_objectSkills.remove(this);

		} else {
			_distance -= 1;
		}
	}

	@Override
	public void hitSprite(Sprite sprite, GameView gameView) {
		if (isCollsionWithRect(_x, _y, bmp.getWidth(), bmp.getHeight(),
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
		}
	}

}
