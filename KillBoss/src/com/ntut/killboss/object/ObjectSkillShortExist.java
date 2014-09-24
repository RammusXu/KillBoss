package com.ntut.killboss.object;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;

import com.ntut.killboss.Constant;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.sprite.DrawBitmapFactory;

public class ObjectSkillShortExist extends ObjectSkill {
	DrawBitmapFactory _drawBitmap;

	public ObjectSkillShortExist(Context context,
			List<ObjectSkill> objectSkills, int x, int y) {

		super(context, objectSkills, Constant.skillDrawbleResourceIDs[3], x, y,
				true);
		_y = GameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM;
		_drawBitmap = new DrawBitmapFactory(bmp, x, y, 5, 3, 11);
	}

	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas) {
		if (_drawBitmap.checkEndAnimation()) {
			_objectSkills.remove(this);
		}
		_drawBitmap.onDraw(canvas);
	}

	public void playAnimation(int x, int y) {
		_drawBitmap.reset(x, y);
		_objectSkills.add(this);
	}

}
