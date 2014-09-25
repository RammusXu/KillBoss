package com.ntut.killboss.sprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ntut.killboss.R;
import com.ntut.killboss.SoundEffect;
import com.ntut.killboss.core.GameView;

public class AnimationReduceHP {
	private static final int SPACE_TO_BORDER = 48;

	private float x;
	private float y;

	private int life = 15;
	private int reduceHP = 1;
	private Paint paint = new Paint();

	public AnimationReduceHP(GameView gameView, float x, float y, int reduceHP) {
		this.x = Math.min(x, GameView._screenSize.x - SPACE_TO_BORDER);
		this.y = Math.min(y, GameView._screenSize.y - SPACE_TO_BORDER);
		this.reduceHP = reduceHP;

		new SoundEffect(gameView.getContext(), R.raw.skill_a);
	}

	private void update() {
		if (--life < 1) {
			GameView._animationReduceHP.remove(this);
		} else {
			if (y > 0) {
				y -= 10;
			}
		}
	}

	public void onDraw(Canvas canvas) {
		update();

		paint.setTextSize(48);
		paint.setColor(Color.RED);
		canvas.drawText("" + reduceHP, x, y, paint);
	}

}
