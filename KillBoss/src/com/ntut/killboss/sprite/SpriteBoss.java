package com.ntut.killboss.sprite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.R;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.menu.StageFragment;
import com.ntut.killboss.object.ObjectSkill;
import com.ntut.killboss.object.ObjectSkill2;

public class SpriteBoss extends Sprite {

	private static final String TAG = "SpriteBoss";

	private int skillSpeed = 40;
	private int skillReleaseFlag = 0;

	public SpriteBoss(Context context, GameView gameView, int bossID) {
		_context = context;
		_gameView = gameView;

		loadBitmap(Constant.bossIDs[StageFragment.bossInt],
				GameView._screenSize.x / 6, GameView._screenSize.y / 6);

		_bitmapMirror = FunctionUtilities.mirrorBitmap2(_bitmap, _width,
				_height);

		_x = GameView._screenSize.x / 3;
		_y = GameView._screenSize.y - _height - Constant.SPACE_TO_BOTTOM - 80;

	}

	private boolean dirFlag = true;

	public void useSkill() {
		skillReleaseFlag = (skillReleaseFlag + 1) % skillSpeed;
		if (skillReleaseFlag == 0) {
			ObjectSkill temp, temp1, temp2;
			dirFlag = !dirFlag;
			if (dirFlag) {

				if (FunctionUtilities.getRandom(2) == 0) {

					temp = new ObjectSkill(_context,
							GameView._objectSkillsBoss, _x + (_width / 2), _y
									+ (_height * 3 / 4), true);
					GameView._objectSkillsBoss.add(temp);
				} else {

					temp = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					temp1 = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					temp2 = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					GameView._objectSkillsBoss.add(temp);
					GameView._objectSkillsBoss.add(temp1);
					GameView._objectSkillsBoss.add(temp2);
				}

			} else {
				if (FunctionUtilities.getRandom(1) == 0) {

					temp = new ObjectSkill(_context,
							GameView._objectSkillsBoss, _x + (_width / 2), _y
									+ (_height * 3 / 4), false);
					GameView._objectSkillsBoss.add(temp);
				} else {
					temp = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					temp1 = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					temp2 = new ObjectSkill2(_context,
							GameView._objectSkillsBoss);
					GameView._objectSkillsBoss.add(temp);
					GameView._objectSkillsBoss.add(temp1);
					GameView._objectSkillsBoss.add(temp2);
				}
			}
			Log.d("DEBUG", GameView._objectSkillsBoss.size() + "");
		} else {
			Log.d("DEBUG", "" + skillReleaseFlag);
		}
	}

	private Rect src = new Rect();

	@Override
	public void onDraw(Canvas canvas) {
		useSkill();

		dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {
			canvas.drawBitmap(_bitmapMirror, null, dst, null);
		} else {
			canvas.drawBitmap(_bitmap, null, dst, null);
		}

		drawHP(canvas);
	}

}
