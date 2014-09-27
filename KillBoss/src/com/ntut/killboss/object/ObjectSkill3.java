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
		_objectSkills = objectSkills;
		_direction = direction;
	}

}