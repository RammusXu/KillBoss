package com.ntut.killboss.object;

import java.util.List;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;

import android.content.Context;

public class ObjectSkill2 extends ObjectSkill {
	private ObjectSkillShortExist temp;

	public ObjectSkill2(Context context, List<ObjectSkill> objectSkills) {

		super(context, objectSkills, Constant.skillDrawbleResourceIDs[1],
				FunctionUtilities.getRandom(GameView._screenSize.x), 0, true);

		temp = new ObjectSkillShortExist(_context, GameView._objectSkillsBoss,
				_x, _y);
	}

	@Override
	protected void update() {
		if (checkYOutOfBound()) {
			_objectSkills.remove(this);
			temp.playAnimation(_x, _y);
		} else {
			_y += _speed;
		}
	}

}
