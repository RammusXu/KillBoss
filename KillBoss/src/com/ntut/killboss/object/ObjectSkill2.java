package com.ntut.killboss.object;

import java.util.List;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;

import android.content.Context;

public class ObjectSkill2 extends ObjectSkill {

	public ObjectSkill2(Context context, List<ObjectSkill> objectSkills) {

		super(context, objectSkills, Constant.skillDrawbleResourceIDs[1],
				FunctionUtilities.getRandom(GameView._screenSize.x), 0, true);
	}

	@Override
	protected void update() {
		if (checkYOutOfBound()) {
			_objectSkills.remove(this);
		} else {
			_y += _speed;
		}
	}

}
