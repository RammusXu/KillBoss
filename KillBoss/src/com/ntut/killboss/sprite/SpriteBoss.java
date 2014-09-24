package com.ntut.killboss.sprite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.core.GameView;
import com.ntut.killboss.menu.StageFragment;
import com.ntut.killboss.object.ObjectSkill;
import com.ntut.killboss.object.ObjectSkill2;
import com.ntut.killboss.object.ObjectSkill3;
import com.ntut.killboss.object.ObjectSkillShortExist;

public class SpriteBoss extends Sprite {
	private static final String TAG = "SpriteBoss";

	// If too small, will cause objectSkill speed up.
	private static final int SKILL_RELEASE_SPEED = 100;
	private int skillReleaseFlag = 0;

	ObjectSkill temp, temp1, temp2, temp3;

	public SpriteBoss(Context context, GameView gameView, int bossID) {
		_context = context;
		_gameView = gameView;

		loadBitmap(Constant.bossIDs[StageFragment.bossInt],
				GameView._screenSize.x / 6, GameView._screenSize.y / 6);

		_bitmapMirror = FunctionUtilities.mirrorBitmap2(_bitmap, _width,
				_height);

		_x = GameView._screenSize.x * 4 / 5;
		_y = GameView._screenSize.y - _height - GameView._screenSize.y / 6;

		temp = new ObjectSkill(_context, GameView._objectSkillsBoss, _x, _y,
				_direction);
		temp1 = new ObjectSkill2(_context, GameView._objectSkillsBoss);
		temp2 = new ObjectSkill2(_context, GameView._objectSkillsBoss);
		temp3 = new ObjectSkill2(_context, GameView._objectSkillsBoss);

	}

	public void useSkill() {
		skillReleaseFlag = (skillReleaseFlag + 1) % SKILL_RELEASE_SPEED;
		if (skillReleaseFlag == 0) {

			skillList();
		}
	}

	private void skillList() {
		if (FunctionUtilities.getRandom(2) == 0) {
			temp.activeObject(_x, _y, _direction);
		} else {

			temp1.activeObject();
			temp2.activeObject();
			temp3.activeObject();
		}
	}

	private Rect src = new Rect();

	private int moveToRandomX = 0;
	private boolean moveToRandomXFlag = true;

	private void moveToX() {
		if (moveToRandomXFlag) {
			_status = SprtieStatus.Run;
			Log.d("DEBUG", "moveToRandomXFlag = false, " + moveToRandomX);
			moveToRandomX = FunctionUtilities.getRandom(GameView._screenSize.x
					- _width);
			moveToRandomXFlag = false;
		} else {
			if (moveToRandomX > _x) {
				_direction = true;
				_x += _speedX;
			} else {
				_direction = false;
				_x -= _speedX;
			}
			if (Math.abs(moveToRandomX - _x) < _speedX) {
				Log.d("DEBUG", "moveToRandomXFlag = true, " + moveToRandomX);
				moveToRandomXFlag = true;

				_status = SprtieStatus.Normal;
				_standCycleFlag = 60;
			}
		}
	}

	private static final int STAND_CYCLE = 60;
	private int _standCycleFlag = 0;

	private void stand() {
		_standCycleFlag = (_standCycleFlag + 1) % STAND_CYCLE;
		if (_standCycleFlag == 0) {
			_status = SprtieStatus.Run;
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		useSkill();

		if (_status == SprtieStatus.Run) {
			moveToX();
		} else if (_status == SprtieStatus.Normal) {
			stand();
		}

		dst.set(_x, _y, _x + _width, _y + _height);

		if (_direction) {
			canvas.drawBitmap(_bitmapMirror, null, dst, null);
		} else {
			canvas.drawBitmap(_bitmap, null, dst, null);
		}

		drawHP(canvas);
	}

	public enum SprtieStatus {
		Normal, Run, Hurt, Skill
	}

	private SprtieStatus _status = SprtieStatus.Normal;

}
